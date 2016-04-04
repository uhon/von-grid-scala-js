package vongrid.config

import org.denigma.threejs.Material
import vongrid.ExtrudeSettings

import scala.scalajs.js

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */
@js.native
trait TileGenConfig extends js.Object {
  var tileScale: Float = js.native
  var cellSize: Float = js.native
  var material: Material = js.native
  var extrudeSettings: ExtrudeSettings = js.native
}
