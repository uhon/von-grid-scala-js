package vongrid.rendering

import org.denigma.threejs._
import vongrid.{Board, Tile}
import vongrid.utils.Tools

import scala.scalajs.js.annotation.ScalaJSDefined

 /**
  * Sprite as it is understud by vg
  */
@ScalaJSDefined
trait BoardSprite extends Sprite {
  var board: Board
  var geo: BufferGeometry
  var url: String
  var container: Object3D
  var texture: Texture

  var highlight: Color

  // how high off the board this object sits
  var heightOffset: Float

  // other objects like the SelectionManager expect these on all objects that are added to the scene
  var active: Boolean
  val uniqueId: String
  var tile: Tile

}