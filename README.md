# Scala.js facades for von-grid

Scala-js facades for awesome [von-grid - 3D hex tile system](https://github.com/vonWolfehaus/von-grid/)

## State of Development

- Implemented facades, initial commit (untested)
- Square-Grid not implemented yet

## Usage

Add facades to your project by adding a sbt-subproject like this to your main build.sbt file:

```scala
lazy val vonGridScalaJs = (project in file("von-grid-scala-js")).settings(
  scalaVersion := scalaV,
  persistLauncher := true,
  //refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile),
  persistLauncher in Test := false,
  resolvers += sbt.Resolver.bintrayRepo("denigma", "denigma-releases"),
  resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.0",
    "org.scala-js" %%% "scalajs-tools" % "0.6.6",
    "org.denigma" %%% "threejs-facade" % "0.0.74-0.1.6"
  ),
  jsDependencies ++= Seq(
    RuntimeDOM,
    "org.webjars" % "three.js" % "r74" / "three.js"
  ),
  persistLauncher in Compile := true,
  skip in packageJSDependencies := false

).enablePlugins(ScalaJSPlugin)
```