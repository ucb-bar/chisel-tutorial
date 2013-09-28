organization := "edu.berkeley.cs"

version := "2.0-SNAPSHOT"

name := "chisel-tutorial"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "scct-github-repository" at "http://mtkopone.github.com/scct/maven-repo"
)

libraryDependencies += "edu.berkeley.cs" %% "chisel" % "latest.release"

