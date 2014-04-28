organization := "edu.berkeley.cs"

version := "2.3-SNAPSHOT"

name := "chisel-tutorial"

scalaVersion := "2.10.2"

addSbtPlugin("com.github.scct" % "sbt-scct" % "0.2")

// Due to project naming, the next only matches released versions.
//libraryDependencies += "edu.berkeley.cs" %% "chisel" % "latest.release"
libraryDependencies += "edu.berkeley.cs" %% "chisel" % "2.3-SNAPSHOT"

