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
trait HexGridConfig extends js.Object
object HexGridConfig extends HexGridConfigBuilder(noOpts)
class HexGridConfigBuilder(val dict:OptMap) extends JSOptionBuilder[HexGridConfig, HexGridConfigBuilder](new HexGridConfigBuilder(_)) {
 def cellSize(v: Int) = jsOpt("cellSize", v)
 def cameraPosition(v: Vector3) = jsOpt("cameraPosition", v)
 def fog(v: Fog) = jsOpt("fog", v)
}
