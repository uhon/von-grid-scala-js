package vongrid.utils

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSName

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSName("vg.Tools")
object Tools extends js.Object {


//  TODO: Implement if needed
//  def clamp(value: Int, min: Int, max: Int): = js.native

//  def sign(val): Float = js.native
//  def now(): = js.native
//
//  def empty(node): = js.native

///
//  @source: http://jsperf.com/radix-sort
//
//  def radixSort(arr: js.Array, idxBegin, idxEnd, bit): js.Array = js.native

  def generateID(): String = js.native

  /**
    * If one value is passed, it will return something from -val to val.
    * Else it returns a value between the range specified by min, max.
    */
  def random(min: Int, max: UndefOr[Int]): Float = js.native

  // from min to (and including) max
  def randomInt(min: Int, max: Int): Int = js.native

  def randomInt(min: Int): Int = js.native

  def normalize(v: Float, min: Int, max: Int): Float = js.native

  def getShortRotation(angle: Float): Float = js.native

  def isPlainObject(obj: js.Object): Boolean = js.native

  // https://github.com/KyleAMathews/deepmerge/blob/master/index.js
  def merge(target: js.Object, src: js.Object): js.Object = js.native




  def randomizeRGB(base: String, range:UndefOr[Int] = js.undefined): Double = js.native

  def getJSON(config: js.Object): String = js.native
}