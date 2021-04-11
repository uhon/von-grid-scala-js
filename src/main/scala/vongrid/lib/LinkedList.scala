package vongrid.lib

import vongrid.config.HexGridConfig

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSImport, JSName}

/**
  * Created by uhon on 27/03/16.
  */
@js.native
trait LinkedList extends js.Object {
  def generateID(): String = js.native
  // TODO implement if needed
}

@js.native
@JSGlobal
object LinkedList extends LinkedList
