package vongrid.config

import com.sun.prism.Material
import org.denigma.threejs.{ExtrudeGeometry, Object3D, Texture}

import scala.scalajs.js

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */
@js.native
trait SpriteConfig extends js.Object {
  var material: Material = null
  var geo: ExtrudeGeometry = null
  var url: String = null
  var container:  Object3D = null
  var texture: Texture = null
  var scale = 1f
  var highlight = "rgb(0 168 228)"
  var heightOffset = 0f // how high off the board this object sits
  var obstacle = false
}
