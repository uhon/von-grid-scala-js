package vongrid.three

import com.sun.prism.Material
import org.denigma.threejs.{ExtrudeGeometry, Object3D, Texture, Vector3}
import vongrid.Tile
import vongrid.config.SpriteConfig
import vongrid.utils.Tools

import scala.scalajs.js


/**
  * Created by uhon on 27/03/16.
  */

@js.native
class Sprite(settings: SpriteConfig) extends js.Object {
  // settings
  var material: Material = js.native
  var geo: ExtrudeGeometry = js.native
  var url: String = js.native
  var container:  Object3D = js.native
  var texture: Texture = js.native
  var scale: Float = js.native
  var highlight: String = js.native
  var heightOffset: Float = js.native // how high off the board this object sits
  var obstacle:Boolean  = js.native


  // other objects like the SelectionManager expect these on all objects that are added to the scene
  var active: Boolean = js.native
  var uniqueId: String = js.native
  var objectType = vongrid.ENT
  var tile: Tile = js.native
  var view: org.denigma.threejs.Sprite = js.native
  var position: Vector3 = js.native

  def activate(x: Float, y: Float, z: Float): js.Any = js.native
  def disable(): js.Any = js.native
  def update(): js.Any = js.native
  def select(): js.Any = js.native
  def deselect(): js.Any = js.native
  def dispose(): js.Any = js.native
}

