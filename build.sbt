name := "scala-playground"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "org.specs2" %% "specs2" % "2.3.12" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")
