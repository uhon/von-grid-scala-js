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
trait AStarFinderConfig extends js.Object
object AStarFinderConfig extends AStarFinderConfigBuilder(noOpts)
class AStarFinderConfigBuilder(val dict:OptMap) extends JSOptionBuilder[AStarFinderConfig, AStarFinderConfigBuilder](new AStarFinderConfigBuilder(_)) {
   def allowDiagonal(v: Boolean) = jsOpt("allowDiagonal", v)
   def heuristicFilter(v: Function1[Cell, Cell] ) = jsOpt("heuristicFilter", v)
}
