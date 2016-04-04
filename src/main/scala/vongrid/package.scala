/**
  * @author Urs Honegger &lt;u.honegger@insign.ch&gt;
  */
package object vongrid {
  val VERSION = "0.1.1"

  val PI = Math.PI
  val TAU = Math.PI * 2 
  val DEG_TO_RAD = 0.0174532925 
  val RAD_TO_DEG = 57.2957795 
  val SQRT3 = Math.sqrt(3)  // used often in hex conversions

  // useful enums for type checking. change to whatever fits your game. these are just examples
  val TILE = "tile"  // visual representation of a grid cell
  val ENT = "entity"  // dynamic things
  val STR = "structure"  // static things

  val HEX = "hex" 
  val SQR = "square" 
  val ABS = "abstract"
}
