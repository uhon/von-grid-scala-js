package vongrid.config

import org.denigma.threejs._
import vongrid.Cell

import scala.scalajs.js
import org.scalajs.dom.document

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */

trait SceneConfig extends js.Object {
    var element = document.body
    var alpha = true
    var antialias = true
    var clearColor = "#fff"
    var sortObjects = false
    var fog: Fog = null
    var light = new DirectionalLight(0xffffff)
    var lightPosition = null
    var cameraType = "PerspectiveCamera"
    var cameraPosition: Vector3 = null // {x y z}
    var orthoZoom = 4
}
