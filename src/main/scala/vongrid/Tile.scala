package vongrid

import org.denigma.threejs._
import vongrid.config.TileConfig
import vongrid.rendering.BoardSprite

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */
@js.native
@JSName("vg.Tile")
class Tile(config: TileConfig) extends js.Object {
  var buttons = new Object3D
  var cell: Cell = js.native
  var uniqueID: String = js.native
  var geometry: ExtrudeGeometry = js.native
  var material: MeshPhongMaterial = js.native
  var objectType = TILE
  var entity: BoardSprite = js.native

  // populate with any extra data needed in your game
  var userData: js.Object = js.native

  var selected: Boolean = js.native

  // Hex Value of Highlighting Color
  var highlight: String = js.native

  var mesh: Mesh = js.native
  var position: Vector3 = js.native
  var rotation: Double = js.native

  var _emissive: Double = js.native


  def select(): Tile = js.native
  def deselct(): Tile = js.native
  def toggle(): Tile = js.native
  def dispose(): js.Any = js.native
}