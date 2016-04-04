package vongrid.config

import vongrid.Cell

import scala.scalajs.js

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */

@js.native
trait AStarFinderConfig extends js.Object{
    var allowDiagonal: Boolean = js.native
    var heuristicFilter: Function1[Cell, Cell]  = js.native
}
