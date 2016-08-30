package vongrid.config

import org.denigma.threejs._
import org.querki.jsext._
import org.scalajs.dom.raw.HTMLElement
import vongrid.Cell

import scala.scalajs.js
import js.{UndefOr, undefined, |}

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */

@js.native
trait TileConfig extends js.Object
object TileConfig extends TileConfigBuilder(noOpts)
class TileConfigBuilder(val dict:OptMap) extends JSOptionBuilder[TileConfig, TileConfigBuilder](new TileConfigBuilder(_)) {
 def size(v: Float) = jsOpt("size", v)
 def scale(v: Float) = jsOpt("scale", v)
 def cell(v: Cell) = jsOpt("cell", v)
 def geometry(v: ExtrudeGeometry) = jsOpt("geometry", v)
 def material(v: Material) = jsOpt("material", v)

}
