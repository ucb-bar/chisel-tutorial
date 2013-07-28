import sbt._
import Keys._

object BuildSettings
{
  val buildOrganization = "edu.berkeley.cs"
  val buildVersion = "2.0"
  val buildScalaVersion = "2.10.2"

  def apply(projectdir: String, subdir: String = "") = {
    Defaults.defaultSettings ++ Seq (
      organization := buildOrganization,
      version      := buildVersion,
      scalaVersion := buildScalaVersion,
      scalaSource in Compile := Path.absolute(file(projectdir + "/src" + subdir)),
      /* To find chisel jars. */
      resolvers ++= Seq(
        "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
        "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
      ),
      libraryDependencies += "edu.berkeley.cs" %% "chisel" % "2.0-SNAPSHOT"
    )
  }
}

object ChiselBuild extends Build
{
  import BuildSettings._

  lazy val tutorialProblems   = Project("tutorialProblems", file("tutorial"), settings = BuildSettings("..", "/problems")) 
  lazy val tutorialSolutions = Project("tutorialSolutions", file("tutorial"), settings = BuildSettings("..", "/solutions"))
}
