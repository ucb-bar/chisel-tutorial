organization := "edu.berkeley.cs"

version := "1.0"

name := "chisel-tutorial"

scalaVersion := "2.11.7"

// Provide a managed dependency on chisel.iotesters.

libraryDependencies ++= "edu.berkeley.cs" %% "chisel-iotesters" % "1.1-BETA-SNAPSHOT"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-language:reflectiveCalls")

