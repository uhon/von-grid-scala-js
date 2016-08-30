package vongrid

import org.denigma.threejs.Vector3

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import vongrid.AbstractCell
/**
  * @author Urs Honegger &ltu.honegger@insign.ch&gt
  */

trait NodeObject {
  val uniqueID = vongrid.lib.LinkedList.generateID
}


@js.native
@JSName("vg.Cell")
class Cell(var q: Double, var r: Double, var s: Double, var h: Double = 0) extends js.Object {

  // optional link to the visual representation's class instance
  // we make it protected, so that it cant be changed from others than its children. This allows getter or setter with optional-type on child)
  protected var tile: Tile = js.native

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

  def copy(cell: Cell) = js.native

  def add(cell: Cell) = js.native

  def equals(cell: Cell): Boolean = js.native
}


object ACell {
  def copy(cell: Cell) = new Cell(0,0,0,0).copy(cell)
}

