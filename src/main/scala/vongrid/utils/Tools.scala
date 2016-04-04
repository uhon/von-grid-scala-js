package vongrid.utils

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSName("vg.Tools")
trait Tools extends js.Object {
  def generateID(): String = js.native
  // TODO implement if needed
}

object Tools extends Tools