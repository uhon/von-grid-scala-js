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
trait ExtrudeSettings extends js.Object
object ExtrudeSettings extends ExtrudeSettingsBuilder(noOpts)
class ExtrudeSettingsBuilder(val dict:OptMap) extends JSOptionBuilder[ExtrudeSettings, ExtrudeSettingsBuilder](new ExtrudeSettingsBuilder(_)) {
 def amount(v: Int) = jsOpt("amount", v)
 def bevelEnabled(v: Boolean) = jsOpt("bevelEnabled", v)
 def bevelSegments(v: Int) = jsOpt("bevelSegments", v)
 def steps(v: Int) = jsOpt("steps", v)
 def bevelSize(v: Float) = jsOpt("bevelSize", v)
 def bevelThickness(v: Float) = jsOpt("bevelThickness", v)
}

