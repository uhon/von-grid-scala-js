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
trait ControlConfig extends js.Object
object ControlConfig extends ControlConfigBuilder(noOpts)
class ControlConfigBuilder(val dict:OptMap) extends JSOptionBuilder[ControlConfig, ControlConfigBuilder](new ControlConfigBuilder(_)) {
    def minDistance(v: Int) = jsOpt("minDistance", v)
    def maxDistance(v: Int) = jsOpt("maxDistance", v)
    def zoomSpeed(v: Double) = jsOpt("zoomSpeed", v)
    def noZoom(v: Boolean) = jsOpt("noZoom", v)
}
