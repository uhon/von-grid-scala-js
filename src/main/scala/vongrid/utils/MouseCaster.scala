package vongrid.utils

import org.denigma.threejs._
import org.scalajs.dom._
import org.scalajs.dom.raw.{HTMLElement, MouseEvent, WheelEvent}
import vongrid.lib.Signal

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.{JSName, JSGlobal}

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSGlobal("vg.MouseCaster")
class MouseCaster(group: Object3D, camera: Camera, element: UndefOr[HTMLElement] = js.native) extends js.Object {
  var down: Boolean = js.native // left click
  var rightDown: Boolean = js.native
  // the object that was just clicked on
  var pickedObject: Object3D = js.native
  // the object currently being 'held'
  var selectedObject: Object3D = js.native
  // store the results of the last cast
  var allHits: js.Array[Object3D] = js.native
  // disable the caster easily to temporarily prevent user input
  var active: Boolean = js.native

  var shift: Boolean = js.native
  var ctrl: Boolean = js.native
  var wheel: Int = js.native

  // you can track exactly where the mouse is in the 3D scene by using the z component
  var position: Vector3 = js.native
  var screenPosition: Vector2 = js.native
  var signal: Signal = js.native

  // behind-the-scenes stuff you shouldn't worry about
  var _camera: Camera = js.native
  var _raycaster: Raycaster = js.native
  var _preventDefault: Boolean = js.native


  def update() = js.native
  def preventDefault() = js.native
  def _onDocumentMouseDown(evt: MouseEvent): js.Any = js.native
  def _onDocumentMouseUp(evt: MouseEvent): js.Any = js.native
  def _onDocumentMouseMove(evt: MouseEvent): js.Any = js.native
  def _onMouseWheel(evt: WheelEvent): js.Any = js.native

}

object MC {
  // statics to describe the events we dispatch
  var OVER = "over"
  var OUT = "out"
  var DOWN = "down"
  var UP = "up"
  var CLICK = "click" // only fires if the user clicked down and up while on the same object
  var WHEEL = "wheel"

  // Specific (not handled by von-grid). // TODO: Remove it or extend MC
  var DRAG = "drag"
}
