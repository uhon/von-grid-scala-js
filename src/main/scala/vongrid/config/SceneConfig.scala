package vongrid.config

import org.denigma.threejs._
import org.querki.jsext._
import org.scalajs.dom.raw.HTMLElement
import scala.scalajs.js
import js.{|, undefined, UndefOr}

/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */

@js.native
trait SceneConfig extends js.Object
object SceneConfig extends SceneConfigBuilder(noOpts)
class SceneConfigBuilder(val dict:OptMap) extends JSOptionBuilder[SceneConfig, SceneConfigBuilder](new SceneConfigBuilder(_)) {
  def element(v: HTMLElement) = jsOpt("element", v)
  def cameraPosition(v: Vector3) = jsOpt("cameraPosition", v) // {x y z}
  def alpha(v: Boolean) = jsOpt("alpha", v)
  def antialias(v: Boolean) = jsOpt("antialias", v)
  def clearColor(v: String) = jsOpt("clearColor", v)
  def sortObjects(v: Boolean) = jsOpt("sortObjects", v)
  def fog(v: Fog) = jsOpt("fog", v)
  def light(v: DirectionalLight) = jsOpt("light", v)
  def lightPosition(v: Vector3) = jsOpt("lightPosition", v)
  def cameraType(v: String) = jsOpt("cameraType", v)
  def orthoZoom(v: Double) = jsOpt("orthoZoom", v)
}