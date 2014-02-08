name := "scala-playground"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.3.7" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")
