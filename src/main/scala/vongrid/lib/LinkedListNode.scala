package vongrid.lib

import vongrid.NodeObject

import scala.scalajs.js

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */
@js.native
trait LinkedListNode extends js.Object {
  var obj: NodeObject = js.native
  var next: LinkedListNode = js.native
  var prev: LinkedListNode = js.native
  var free: Boolean = js.native
}
