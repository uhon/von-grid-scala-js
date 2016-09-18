package vongrid.utils

import org.denigma.threejs.{DirectionalLight, _}
import org.denigma.threejs
import org.denigma.threejs.extras.OrbitControls
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLElement
import vongrid.config.{ControlConfig, SceneConfig}
import vongrid.controls.OrbitControlsPort

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSName("vg.Scene")
class Scene() extends js.Object {
  def this(sceneConfig: SceneConfig, controlConfig: ControlConfig) = this()
  def this(sceneConfig: SceneConfig, controlConfig: Boolean) = this()

  var controls: OrbitControlsPort = js.native
  val renderer: WebGLRenderer = js.native
  var width: Int = js.native
  var height: Int = js.native
  var container: threejs.Scene = js.native
  var camera: Camera = js.native
  var controlled: Boolean = js.native


  def attachTo(element: Element) = js.native
  def add(obj: Object3D) = js.native
  def remove(obj: Object3D) = js.native
  def render() = js.native
  def updateOrthoZoom() = js.native
  def focusOn(obj: Object3D) = js.native
}

@js.native
trait SceneSettings extends js.Object {
  var element: Element = js.native
  var alpha: Boolean = js.native
  var antialias: Boolean = js.native
  var clearColor: String = js.native
  var sortObjects: Boolean = js.native
  var fog: Fog = js.native
  var light: DirectionalLight = js.native
  var lightPosition: Vector3 = js.native
  var cameraType: String = js.native
  var cameraPosition: Vector3 = js.native
  var orthoZoom:Int = js.native
}

@js.native
trait ControlSettings extends js.Object {
  var minDistance: Int = js.native
  var maxDistance: Int = js.native
  var zoomSpeed: Int = js.native
  var noZoom = js.native
}
