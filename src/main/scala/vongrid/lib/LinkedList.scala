package vongrid.lib

import vongrid.config.HexGridConfig

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSName("vg.LinkedList")
trait LinkedList extends js.Object {
  def generateID(): String = js.native
  // TODO implement if needed
}

object LinkedList extends LinkedList