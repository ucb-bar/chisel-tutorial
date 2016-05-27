organization := "edu.berkeley.cs"

version := "3.0-BETA-SNAPSHOT"

name := "chisel-tutorial"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-language:reflectiveCalls")

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.0-BETA-SNAPSHOT"

libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.1-BETA-SNAPSHOT"
