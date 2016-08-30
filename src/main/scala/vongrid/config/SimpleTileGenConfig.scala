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
trait SimpleTileGenConfig extends js.Object
object SimpleTileGenConfig extends SimpleTileGenConfigBuilder(noOpts)
class SimpleTileGenConfigBuilder(val dict:OptMap) extends JSOptionBuilder[SimpleTileGenConfig, SimpleTileGenConfigBuilder](new SimpleTileGenConfigBuilder(_)) {
 def size(v: Float) = jsOpt("size", v)
}
