package vongrid

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * @author Urs Honegger &ltu.honegger@insign.ch&gt
  */

trait NodeObject {
  val uniqueID = vongrid.lib.LinkedList.generateID
}


@js.native
@JSName("vg.Cell")
class Cell(var q: Float, var r: Float, var s: Float, var h: Float) extends js.Object {

  // optional link to the visual representation's class instance
  var tile: Tile = js.native

  // if true, pathfinder will use as a through node
  // rest of these are used by the pathfinder and overwritten at runtime, so don't touch
  var walkable: Boolean = js.native

  // populate with any extra data needed in your game
  var userData: js.Object = js.native

  var _calcCost: Int = js.native
  var _priority: Int = js.native
  var _visited: Boolean = js.native
  var _parent: js.Any = js.native

  def set(q: Float, r: Float, s: Float) = js.native

  def copy(cell: Cell): Cell = js.native

  def add(cell: Cell): Cell = js.native

  def equals(cell: Cell): Cell = js.native
}
