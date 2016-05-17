// Provide a managed dependency on chisel.
// The default version is "latest.release".
// This may be overridden if -DchiselVersion="" is supplied on the command line.

val chiselVersion_h = System.getProperty("chiselVersion", "3.0")

libraryDependencies ++= ( if (chiselVersion_h != "None" ) ("edu.berkeley.cs" %% "chisel3" % chiselVersion_h) :: Nil; else Nil)

libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.1-BETA-SNAPSHOT"
