package vongrid.pathing

import vongrid.config.AStarFinderConfig
import vongrid.lib.LinkedList
import vongrid.{Cell, HexGrid}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, JSGlobal}

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSGlobal("vg.AStarFinder")
class AStarFinder(finderConfig: js.UndefOr[AStarFinderConfig]) extends js.Object {
  var allowDiagonal: Boolean = js.native
  var heuristicFilter: Function[Cell, Cell] = js.native
  var list: LinkedList = js.native

  def findPath(startNode: Cell, endNode: Cell, heuristic: Function[Cell, Cell], grid: HexGrid): Array[Cell] = js.native
  def compare(nodeA: Cell, nodeB: Cell): Function[Cell, Cell] = js.native
}
