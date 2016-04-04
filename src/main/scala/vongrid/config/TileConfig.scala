package vongrid.config

import org.denigma.threejs.{ExtrudeGeometry, Material}
import vongrid.Cell

import scala.scalajs.js

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */
@js.native
trait TileConfig extends js.Object {
  var size: Float = js.native
  var scale: Float = js.native
  var cell: Cell = js.native
  var geometry: ExtrudeGeometry = js.native
  var material: Material = js.native

}
