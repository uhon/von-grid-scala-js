package vongrid.config

import org.denigma.threejs._
import org.scalajs.dom.document

import scala.scalajs.js

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */

@js.native
trait ControlConfig extends js.Object {
    var minDistance: Int = 100
    var maxDistance: Int = 1000
    var zoomSpeed: Int = 2
    var noZoom = false
}
