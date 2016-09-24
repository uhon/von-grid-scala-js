package vongrid.controls

import org.querki.jquery._
import org.denigma.threejs.{EventDispatcher, PerspectiveCamera, _}
import org.scalajs.dom._
import org.scalajs.dom.raw.{Event, HTMLElement}

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g, literal => l}
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

object Keys extends Enumeration {
  type Keys = Value
  val LEFT = Value(37)
  val UP = Value(38)
  val RIGHT = Value(39)
  val BOTTOM = Value(40)
}

object Mouse {
  val LEFT = 0
  val MIDDLE = 1
  val RIGHT = 2
}

trait MouseControls {
  val ORBIT = Mouse.LEFT
  val ZOOM = Mouse.MIDDLE
  val PAN = Mouse.RIGHT
}

object Fingers {
  val ONE = 1
  val TWO = 2
  val THREE = 3
}

trait TouchControls {
  val ORBIT = Fingers.ONE
  val ZOOM = Fingers.TWO
  val PAN = Fingers.THREE
}


object DefaultMouseControls extends MouseControls
object DefaultTouchControls extends TouchControls

object State {
  var NONE = -1
  var ROTATE = 0
  var DOLLY = 1
  var PAN = 2
  var TOUCH_ROTATE = 3
  var TOUCH_DOLLY = 4
  var TOUCH_PAN = 5
}

/**
  * @author Urs Honegger &ltu.honegger@insign.ch&gt
  */
@ScalaJSDefined
@JSName("THREE.OrbitControlsPort")
class OrbitControlsPort(camera: Camera, element: HTMLElement, mouseControls: MouseControls = DefaultMouseControls, touchControl: TouchControls = DefaultTouchControls) extends EventDispatcher {
  var domElement = element
  // Set to false to disable this control
  var enabled = true
  // "target" sets the location of focus, where the camera orbits around
  var target = new Vector3
  // How far you can dolly in and out ( PerspectiveCamera only )
  var minDistance = 0d
  var maxDistance = Double.MaxValue
  // How far you can zoom in and out ( OrthographicCamera only )
  var minZoom = 0d
  var maxZoom = Double.MaxValue
  // How far you can orbit vertically, upper and lower limits.
  // Range is 0 to Math.PI radians.
  var minPolarAngle = 0d
  // radians
  var maxPolarAngle = Math.PI
  // radians
  // How far you can orbit horizontally, upper and lower limits.
  // If set, must be a sub-interval of the interval [ - Math.PI, Math.PI ].
  var minAzimuthAngle = -Double.MaxValue
  // radians
  var maxAzimuthAngle = Double.MaxValue
  // radians
  // Set to true to enable damping (inertia)
  // If damping is enabled, you must call controls.update() in your animation loop
  var enableDamping = false
  var dampingFactor = 0.25d
  // This option actually enables dollying in and out left as "zoom" for backwards compatibility.
  // Set to false to disable zooming
  var enableZoom = true
  var zoomSpeed = 0.2d
  // Set to false to disable rotating
  var enableRotate = true
  var rotateSpeed = 0.2d
  // Set to false to disable panning
  var enablePan = true
  var keyPanSpeed = 7.0d
  // pixels moved per arrow key push
  // Set to true to automatically rotate around the target
  // If auto-rotate is enabled, you must call controls.update() in your animation loop
  var autoRotate = false
  var autoRotateSpeed = 2.0d
  // 30 seconds per round when fps is 60
  // Set to false to disable use of the keys
  var enableKeys = true
  // for reset
  var target0 = target.clone
  var position0 = camera.position.clone
  var zoom0 = camera match {
    case c: OrthographicCamera => c.zoom
    case _ => 0
  }
  //
  // public methods
  //


  def getPolarAngle() {
    return spherical.phi
  }


  def getAzimuthalAngle() {
    return spherical.theta
  }


  def reset() {
    target.copy(target0)
    camera.position.copy(position0)
    camera match {
      case c: OrthographicCamera => c.zoom = zoom0; c.updateProjectionMatrix
      case _ =>
    }
//    console.log("dispatchEvent", changeEvent)
    dispatchEvent(changeEvent)
    update
    state = STATE.NONE
  }

  // this method is exposed, but perhaps it would be better if we can make it private...


  def update: () => Boolean = {
    //    console.log("update")
    var offset = new Vector3
    // so camera.up is the orbit axis
    var quat = new Quaternion().setFromUnitVectors(camera.up, new Vector3(0, 1, 0))
    var quatInverse = quat.clone().inverse
    var lastPosition = new Vector3
    var lastQuaternion = new Quaternion
    () => {
      var position = camera.position
      offset.copy(position).sub(target)
      // rotate offset to "y-axis-is-up" space
      offset.applyQuaternion(quat)
      // angle from z-axis around y-axis
      spherical.setFromVector3(offset)
      if (autoRotate && state == STATE.NONE) {
        rotateLeft(getAutoRotationAngle)
      }
      spherical.theta += sphericalDelta.theta
      spherical.phi += sphericalDelta.phi
      // restrict theta to be between desired limits
      spherical.theta = Math.max(minAzimuthAngle, Math.min(maxAzimuthAngle, spherical.theta))
      // restrict phi to be between desired limits
      spherical.phi = Math.max(minPolarAngle, Math.min(maxPolarAngle, spherical.phi))
      spherical.makeSafe
      spherical.radius *= scale
      // restrict radius to be between desired limits
      spherical.radius = Math.max(minDistance, Math.min(maxDistance, spherical.radius))
      // move target to panned location
      target.add(panOffset)
      offset.setFromSpherical(spherical)
      // rotate offset back to "camera-up-vector-is-up" space
      offset.applyQuaternion(quatInverse)
      position.copy(target).add(offset)
      camera.lookAt(target)
      if (enableDamping == true) {
        sphericalDelta.theta *= (1 - dampingFactor)
        sphericalDelta.phi *= (1 - dampingFactor)
      } else {
        sphericalDelta.set(0, 0, 0)
      }
      scale = 1
      panOffset.set(0, 0, 0)
      // update condition is:
      // min(camera displacement, camera rotation in radians)^2 > EPS
      // using small-angle approximation cos(x/2) = 1 - x^2 / 8
      if (zoomChanged ||
        lastPosition.distanceToSquared(camera.position) > EPS ||
        8 * (1 - lastQuaternion.dot(camera.quaternion)) > EPS) {
//        dispatchEvent(changeEvent)
        lastPosition.copy(camera.position)
        lastQuaternion.copy(camera.quaternion)
        zoomChanged = false
        true
      }
      false
    }
  }


  def dispose() {
    domElement.removeEventListener("contextmenu", (onContextMenu _).asInstanceOf[Function[Event, _]], false)
    domElement.removeEventListener("mousedown", (onMouseDown _).asInstanceOf[Function[Event, _]], false)
    domElement.removeEventListener("wheel", (onMouseWheel _).asInstanceOf[Function[Event, _]], false)
    domElement.removeEventListener("touchstart", (onTouchStart _).asInstanceOf[Function[Event, _]], false)
    domElement.removeEventListener("touchend", (onTouchEnd _).asInstanceOf[Function[Event, _]], false)
    domElement.removeEventListener("touchmove", (onTouchMove _).asInstanceOf[Function[Event, _]], false)
    document.removeEventListener("mousemove", (onMouseMove _).asInstanceOf[Function[Event, _]], false)
    document.removeEventListener("mouseup", (onMouseUp _).asInstanceOf[Function[Event, _]], false)
    window.removeEventListener("keydown", (onKeyDown _).asInstanceOf[Function[Event, _]], false)
    window.removeEventListener("keyup", (onKeyUp _).asInstanceOf[Function[Event, _]], false)
    //dispatchEvent( { type: "dispose" } ) // should this be added here?
  }

  //
  // internals
  //
  var endEvent = l(`type` = "end")
  var startEvent = l(`type` = "start")
  var changeEvent = l(`type` = "change")
  var STATE = State
  var state = STATE.NONE
  var EPS = 0.000001
  // current position in spherical coordinates
  var spherical = new Spherical
  var sphericalDelta = new Spherical
  var scale = 1d
  var panOffset = new Vector3
  var zoomChanged = false
  var rotateStart = new Vector2
  var rotateEnd = new Vector2
  var rotateDelta = new Vector2
  var panStart = new Vector2
  var panEnd = new Vector2
  var panDelta = new Vector2
  var dollyStart = new Vector2
  var dollyEnd = new Vector2
  var dollyDelta = new Vector2

  private


  def getAutoRotationAngle = 2 * Math.PI / 60 / 60 * autoRotateSpeed

  private


  def getZoomScale = Math.pow(0.95, zoomSpeed)

  private


  def rotateLeft(angle: Double) = sphericalDelta.theta -= angle

  private


  def rotateUp(angle: Double) = sphericalDelta.phi -= angle


  def panLeft() = {
    var v = new Vector3
    (distance: Double, objectMatrix: Matrix4) => {
      v.setFromMatrixColumn(objectMatrix, 0) // get X column of objectMatrix
      v.multiplyScalar(-distance)
      panOffset.add(v)
    }
  }


  def panUp() = {
    var v = new Vector3
    (distance: Double, objectMatrix: Matrix4) => {
      v.setFromMatrixColumn(objectMatrix, 1) // get Y column of objectMatrix
      v.multiplyScalar(distance)
      panOffset.add(v)
    }
  }

  // deltaX and deltaY are in pixels right and down are positive


  def pan() = {
    var offset = new Vector3
    (deltaX: Double, deltaY: Double) => {
      var element = if (domElement.equals(document)) document.body else domElement
      camera match {
        case c: PerspectiveCamera => {
          // perspective
          var position = c.position
          offset.copy(position).sub(target)
          var targetDistance = offset.length
          // half of the fov is center to top of screen
          targetDistance *= Math.tan((c.fov / 2) * Math.PI / 180.0)
          // we actually don"t use screenWidth, since perspective camera is fixed to screen height
          panLeft()(2 * deltaX * targetDistance / element.clientHeight, camera.matrix)
          panUp()(2 * deltaY * targetDistance / element.clientHeight, camera.matrix)
        }
        case c: OrthographicCamera => {
          // orthographic
          panLeft()(deltaX * (c.right - c.left) / c.zoom / element.clientWidth, c.matrix)
          panUp()(deltaY * (c.top - c.bottom) / c.zoom / element.clientHeight, c.matrix)
        }
        case _ => {
          // camera neither orthographic nor perspective
          console.warn("WARNING: OrbitControls.js encountered an unknown camera type - pan disabled.")
          enablePan = false
        }
      }
    }
  }


  def dollyIn(dollyScale: Double): Unit = {
    camera match {
      case c: PerspectiveCamera => scale = scale / dollyScale
      case c: OrthographicCamera => {
        c.zoom = Math.max(minZoom, Math.min(maxZoom, c.zoom * dollyScale))
        c.updateProjectionMatrix
        zoomChanged = true
      }
      case _ => {
        console.warn("WARNING: OrbitControls.js encountered an unknown camera type - dolly/zoom disabled.")
        enableZoom = false
      }
    }
  }


  def dollyOut(dollyScale: Double): Unit = {
    camera match {
      case c: PerspectiveCamera => scale = scale * dollyScale
      case c: OrthographicCamera => {
        c.zoom = Math.max(minZoom, Math.min(maxZoom, c.zoom / dollyScale))
        c.updateProjectionMatrix
        zoomChanged = true
      }
      case _ =>
        console.warn("WARNING: OrbitControls.js encountered an unknown camera type - dolly/zoom disabled.")
        enableZoom = false
    }
  }

  //
  // event callbacks - update the camera state
  //


  def handleMouseDownRotate(event: MouseEvent) = {
    //console.log( "handleMouseDownRotate" )
    rotateStart.set(event.clientX, event.clientY)
  }


  def handleMouseDownDolly(event: MouseEvent) = {
    //console.log( "handleMouseDownDolly" )
    dollyStart.set(event.clientX, event.clientY)
  }


  def handleMouseDownPan(event: MouseEvent) = {
    //console.log( "handleMouseDownPan" )
    panStart.set(event.clientX, event.clientY)
  }


  def handleMouseMoveRotate(event: MouseEvent) = {
    //console.log( "handleMouseMoveRotate" )
    rotateEnd.set(event.clientX, event.clientY)
    rotateDelta.subVectors(rotateEnd, rotateStart)
    var element = if (domElement == document) document.body else domElement
    // rotating across whole screen goes 360 degrees around
    rotateLeft(2 * Math.PI * rotateDelta.x / element.clientWidth * rotateSpeed)
    // rotating up and down along whole screen attempts to go 360, but limited to 180
    rotateUp(2 * Math.PI * rotateDelta.y / element.clientHeight * rotateSpeed)
    rotateStart.copy(rotateEnd)
    update
  }


  def handleMouseMoveDolly(event: MouseEvent) = {
    //console.log( "handleMouseMoveDolly" )
    dollyEnd.set(event.clientX, event.clientY)
    dollyDelta.subVectors(dollyEnd, dollyStart)
    if (dollyDelta.y > 0) {
      dollyIn(getZoomScale)
    } else if (dollyDelta.y < 0) {
      dollyOut(getZoomScale)
    }
    dollyStart.copy(dollyEnd)
    update
  }


  def handleMouseMovePan(event: MouseEvent) = {
    //console.log( "handleMouseMovePan" )
    panEnd.set(event.clientX, event.clientY)
    panDelta.subVectors(panEnd, panStart)
    pan()(panDelta.x, panDelta.y)
    panStart.copy(panEnd)
    update
  }


  def handleMouseUp(event: MouseEvent) = {
    //console.log( "handleMouseUp" )
  }


  def handleMouseWheel(event: WheelEvent) = {
    //console.log( "handleMouseWheel" )
    if (event.deltaY < 0) {
      dollyOut(getZoomScale)
    } else if (event.deltaY > 0) {
      dollyIn(getZoomScale)
    }
    update
  }


  var activeKeys = List[Int]()

  def handleKeyDown(event: JQueryEventObject) = {
    activeKeys = (event.which :: activeKeys).distinct
    //console.log( "handleKeyDown" )

    var y, x = 0d

    y = y + activeKeys.find(_ == 38).map(_ => keyPanSpeed).getOrElse(0d)
    y = y + activeKeys.find(_ == 40).map(_ => -keyPanSpeed).getOrElse(0d)
    x = x + activeKeys.find(_ == 37).map(_ => keyPanSpeed).getOrElse(0d)
    x = x + activeKeys.find(_ == 39).map(_ => -keyPanSpeed).getOrElse(0d)

    if (x != 0 || y != 0) {
      pan()(x, y)
      update
    }
  }

  def handleKeyUp(event: KeyboardEvent) = {
    console.log("active keys", activeKeys.mkString(","))
    activeKeys = activeKeys.filterNot(_ == event.keyCode)
    console.log("active keys", activeKeys.mkString(","))
  }

  def handleTouchStartRotate(event: TouchEvent) = {
    //console.log( "handleTouchStartRotate" )
    rotateStart.set(event.touches(0).pageX, event.touches(0).pageY)
  }


  def handleTouchStartDolly(event: TouchEvent) = {
    //console.log( "handleTouchStartDolly" )
    var dx = event.touches(0).pageX - event.touches(1).pageX
    var dy = event.touches(0).pageY - event.touches(1).pageY
    var distance = Math.sqrt(dx * dx + dy * dy)
    dollyStart.set(0, distance)
  }


  def handleTouchStartPan(event: TouchEvent) = {
    //console.log( "handleTouchStartPan" )
    panStart.set(event.touches(0).pageX, event.touches(0).pageY)
  }


  def handleTouchMoveRotate(event: TouchEvent) = {
    //console.log( "handleTouchMoveRotate" )
    rotateEnd.set(event.touches(0).pageX, event.touches(0).pageY)
    rotateDelta.subVectors(rotateEnd, rotateStart)
    var element = if (domElement == document) document.body else domElement
    // rotating across whole screen goes 360 degrees around
    rotateLeft(2 * Math.PI * rotateDelta.x / element.clientWidth * rotateSpeed)
    // rotating up and down along whole screen attempts to go 360, but limited to 180
    rotateUp(2 * Math.PI * rotateDelta.y / element.clientHeight * rotateSpeed)
    rotateStart.copy(rotateEnd)
    update
  }


  def handleTouchMoveDolly(event: TouchEvent) = this.synchronized {
    //    console.log("handleTouchMoveDolly")
    var dx = event.touches(0).pageX - event.touches(1).pageX
    var dy = event.touches(0).pageY - event.touches(1).pageY
    var distance = Math.sqrt(dx * dx + dy * dy)
    dollyEnd.set(0, distance)
    dollyDelta.subVectors(dollyEnd, dollyStart)
    if (dollyDelta.y > 0) {
      dollyOut(getZoomScale)
    } else if (dollyDelta.y < 0) {
      dollyIn(getZoomScale)
    }
    dollyStart.copy(dollyEnd)
    update
  }


  def handleTouchMovePan(event: TouchEvent) = {
    //console.log( "handleTouchMovePan" )
    panEnd.set(event.touches(0).pageX, event.touches(0).pageY)
    panDelta.subVectors(panEnd, panStart)
    pan()(panDelta.x, panDelta.y)
    panStart.copy(panEnd)
    update
  }


  def handleTouchEnd(event: TouchEvent) = {
    //console.log( "handleTouchEnd" )
  }

  //
  // event handlers - FSM: listen for events and reset state
  //


  def onMouseDown(event: MouseEvent): Unit = {
    if (!enabled) return
    event.preventDefault
    if (event.button == mouseControls.ORBIT) {
      if (!enableRotate) return
      handleMouseDownRotate(event)
      state = STATE.ROTATE
    } else if (event.button == mouseControls.ZOOM) {
      if (!enableZoom) return
      handleMouseDownDolly(event)
      state = STATE.DOLLY
    } else if (event.button == mouseControls.PAN) {
      if (!enablePan) return
      handleMouseDownPan(event)
      state = STATE.PAN
    }
    if (state != STATE.NONE) {
      document.addEventListener("mousemove", (onMouseMove _).asInstanceOf[Function[Event, _]], false)
      document.addEventListener("mouseup", (onMouseUp _).asInstanceOf[Function[Event, _]], false)
//      console.log("dispatchEvent", startEvent)
      dispatchEvent(startEvent)
    }
  }


  def onMouseMove(event: MouseEvent): Unit = {
    if (enabled == false) return
    event.preventDefault
    if (state == STATE.ROTATE) {
      if (enableRotate == false) return
      handleMouseMoveRotate(event)
    } else if (state == STATE.DOLLY) {
      if (enableZoom == false) return
      handleMouseMoveDolly(event)
    } else if (state == STATE.PAN) {
      if (enablePan == false) return
      handleMouseMovePan(event)
    }
    if (state != STATE.NONE) {
//      console.log("dispatchEvent", changeEvent)
      dispatchEvent(changeEvent)
    }
  }


  def onMouseUp(event: MouseEvent): Unit = {
    if (enabled == false) return
    handleMouseUp(event)
    document.removeEventListener("mousemove", (onMouseMove _).asInstanceOf[Function[Event, _]], false)
    document.removeEventListener("mouseup", (onMouseUp _).asInstanceOf[Function[Event, _]], false)
//    console.log("dispatchEvent", endEvent)
    dispatchEvent(endEvent)
    state = STATE.NONE
  }


  def onMouseWheel(event: WheelEvent): Unit = {
    if (enabled == false || enableZoom == false || (state != STATE.NONE && state != STATE.ROTATE)) return
    event.preventDefault
    event.stopPropagation
    handleMouseWheel(event)
    // not sure why these are here...
//    console.log("dispatchEvent", startEvent)
    dispatchEvent(startEvent)
//    console.log("dispatchEvent", endEvent)
    dispatchEvent(endEvent)
  }


  def onKeyDown(event: JQueryEventObject): Unit = {
    if (!enabled || !enableKeys || !enablePan) return
    handleKeyDown(event)
    dispatchEvent(changeEvent)
  }

  def onKeyUp(event: KeyboardEvent): Unit = {
    if (!enabled || !enableKeys || !enablePan) return
    handleKeyUp(event)
    dispatchEvent(endEvent)
  }



  def onTouchStart(event: TouchEvent): Unit = {
    if (enabled == false) return
    event.touches.length match {
      case touchControl.ORBIT => {
        // one-fingered touch: rotate
        if (enableRotate == false) return
        handleTouchStartRotate(event)
        state = STATE.TOUCH_ROTATE
      }
      case touchControl.ZOOM => {
        // two-fingered touch: dolly
        if (enableZoom == false) return
        handleTouchStartDolly(event)
        state = STATE.TOUCH_DOLLY
      }
      case touchControl.PAN => {
        // three-fingered touch: pan
        if (enablePan == false) return
        handleTouchStartPan(event)
        state = STATE.TOUCH_PAN
      }
      case _ => state = STATE.NONE
    }
    if (state != STATE.NONE) {
//      console.log("dispatchEvent", startEvent)
      dispatchEvent(startEvent)
    }
  }


  def onTouchMove(event: TouchEvent): Unit = {
    if (enabled == false) return
    event.preventDefault
    event.stopPropagation
    event.touches.length match {
      case touchControl.ORBIT => {
        // one-fingered touch: rotate
        if (enableRotate == false) return
        if (state != STATE.TOUCH_ROTATE) return // is this needed?...
        handleTouchMoveRotate(event)
      }
      case touchControl.ZOOM => {
        // two-fingered touch: dolly
        if (enableZoom == false) return
        if (state != STATE.TOUCH_DOLLY) return // is this needed?...
        handleTouchMoveDolly(event)
      }
      case touchControl.PAN => {
        // three-fingered touch: pan
        if (enablePan == false) return
        if (state != STATE.TOUCH_PAN) return // is this needed?...
        handleTouchMovePan(event)
      }
      case _ => state = STATE.NONE
    }
    dispatchEvent(changeEvent)
  }

  def onTouchEnd(event: TouchEvent): Unit = {
    if (enabled == false) return
    handleTouchEnd(event)
//    console.log("dispatchEvent", endEvent)
    state = STATE.NONE
    dispatchEvent(endEvent)
  }


  def onContextMenu(event: MouseEvent) = {
    event.preventDefault
  }

  //
  domElement.addEventListener("contextmenu", (onContextMenu _).asInstanceOf[Function[Event, _]], false)
  domElement.addEventListener("mousedown", (onMouseDown _).asInstanceOf[Function[Event, _]], false)
  domElement.addEventListener("wheel", (onMouseWheel _).asInstanceOf[Function[Event, _]], false)
  domElement.addEventListener("touchstart", (onTouchStart _).asInstanceOf[Function[Event, _]], false)
  domElement.addEventListener("touchend", (onTouchEnd _).asInstanceOf[Function[Event, _]], false)
  domElement.addEventListener("touchmove", (onTouchMove _).asInstanceOf[Function[Event, _]], false)
  // TODO: Handle KeyDown with Jquery (attempt to work around missing keydown event when second arrow
  // key is released, Chrome specific? Firefox works)
  $(window).keydown(onKeyDown _)
//  window.addEventListener("keydown", (onKeyDown _).asInstanceOf[Function[Event, _]], false)
  window.addEventListener("keyup", (onKeyUp _).asInstanceOf[Function[Event, _]], false)
  // force an update at start
  update
}
