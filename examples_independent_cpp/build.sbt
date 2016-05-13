organization := "edu.berkeley.cs"

version := "1.0"

name := "chisel-tutorial"

scalaVersion := "2.11.7"

// Provide a managed dependency on chisel.
// The default version is "3.0".
// This may be overridden if -DchiselVersion="" is supplied on the command line.

val chiselVersion = System.getProperty("chiselVersion", "3.0")

libraryDependencies ++= (
  if (chiselVersion != "None" ) {
      if (chiselVersion.charAt(0)> '2') {
        ("edu.berkeley.cs" %% "chisel3" % chiselVersion) :: ("edu.berkeley.cs" %% "chisel-iotesters" % "1.0") :: Nil
      }
      else {
        ("edu.berkeley.cs" %% "chisel" % chiselVersion) :: Nil
      }
  }
  else Nil
)

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-language:reflectiveCalls")

