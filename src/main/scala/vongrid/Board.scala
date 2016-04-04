package vongrid

import org.denigma.threejs.{LineBasicMaterial, Object3D}
import vongrid.config.{AStarFinderConfig, TileGenConfig}
import vongrid.pathing.AStarFinder
import vongrid.three.Sprite

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSName("vg.Board")
class Board(var grid: HexGrid, finderConfig: AStarFinderConfig) extends js.Object {
  def this(grid: HexGrid) = this(grid, ???)
  var tiles: js.Array[Tile] = js.native
  var tileGroup: Object3D = js.native// only for tiles
  var group: Object3D = js.native // can hold all entities, also holds tileGroup, never trashed
  var overlay: Object3D = js.native

  val finder = new AStarFinder(finderConfig)

  def setEntityOnTile(entity: Sprite, tile: Tile): js.Any = js.native
  def addTile(tile: Tile): js.Any = js.native
  def removeTile(tile: Tile): js.Any = js.native
  def removeAllTiles(): js.Any = js.native
  def getTileAtCell(cell: Cell): js.UndefOr[Tile] = js.native
  // TODO: implement def snapToGrid(pos: ???): js.Any = js.native if necessary
  def snapTileToGrid(tile: Tile): Tile = js.native
  def getRandomTile():Tile = js.native
  def findPath(startTile: Tile, endTile: Tile): Array[Cell] = js.native
  def setGrid(newGrid: HexGrid ): js.Any = js.native
  def generateOverlay(size: Integer): js.Any = js.native
  def generateTilemap(config: TileGenConfig): js.Any = js.native
  def reset(): js.Any = js.native
}