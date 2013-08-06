organization := "edu.berkeley.cs"

version := "2.0-SNAPSHOT"

name := "chisel-tutorial"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies += "edu.berkeley.cs" %% "chisel" % "2.0-SNAPSHOT"

