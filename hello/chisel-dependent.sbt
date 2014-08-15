// Provide a managed dependency on chisel.
// The default version is "latest.release".
// This may be overridden if -DchiselVersion="" is supplied on the command line.

val chiselVersion = System.getProperty("chiselVersion", "latest.release")

libraryDependencies ++= ( if (chiselVersion != "None" ) ("edu.berkeley.cs" %% "chisel" % chiselVersion) :: Nil; else Nil)
