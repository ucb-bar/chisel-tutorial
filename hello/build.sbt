lazy val buildSettings = Seq (
    organization := "edu.berkeley.cs",
    name := "chisel-tutorial",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-language:reflectiveCalls")
)
lazy val root = (project in file(".")).settings(buildSettings: _*).dependsOn(chisel, chiselTesters)

lazy val chisel = project in file("chisel3")
lazy val chiselTesters = project in file("chisel-testers")
