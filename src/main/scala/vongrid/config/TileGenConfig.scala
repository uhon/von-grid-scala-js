package vongrid.config

import org.denigma.threejs._
import org.querki.jsext._
import org.scalajs.dom.raw.HTMLElement
import scala.scalajs.js
import js.{|, undefined, UndefOr}

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */

@js.native
trait TileGenConfig extends js.Object
object TileGenConfig extends TileGenConfigBuilder(noOpts)
class TileGenConfigBuilder(val dict:OptMap) extends JSOptionBuilder[TileGenConfig, TileGenConfigBuilder](new TileGenConfigBuilder(_)) {
 def tileScale(v: Float) = jsOpt("tileScale", v)
 def cellSize(v: Float) = jsOpt("cellSize", v)
 def material(v: Material) = jsOpt("material", v)
 def extrudeSettings(v: ExtrudeSettings) = jsOpt("extrudeSettings", v)
}
