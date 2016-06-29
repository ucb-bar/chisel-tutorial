// Provide a managed dependency on chisel.
// The default version is "2.+".
// This may be overridden if -DchiselVersion="" is supplied on the command line.

val chiselVersion = System.getProperty("chiselVersion", "2.+")

libraryDependencies ++= ( if (chiselVersion != "None" ) ("edu.berkeley.cs" %% "chisel" % chiselVersion) :: Nil; else Nil)
