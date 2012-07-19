import sbt._
import Keys._

object BuildSettings
{
  val buildOrganization = "edu.berkeley.cs"
  val buildVersion = "1.1"
  val buildScalaVersion = "2.9.2"

  def apply(projectdir: String, subdir: String = "") = {
    Defaults.defaultSettings ++ Seq (
      organization := buildOrganization,
      version      := buildVersion,
      scalaVersion := buildScalaVersion,
      scalaSource in Compile := Path.absolute(file(projectdir + "/src" + subdir))
    )
  }
}

object ChiselBuild extends Build
{
  import BuildSettings._

  lazy val chisel = Project("chisel", file("chisel"), settings = BuildSettings(java.lang.System.getenv("CHISEL")))
  lazy val tutorialProblems   = Project("tutorialProblems", file("tutorial"), settings = BuildSettings("..", "/problems")) dependsOn(chisel)
  lazy val tutorialSolutions = Project("tutorialSolutions", file("tutorial"), settings = BuildSettings("..", "/solutions")) dependsOn(chisel)
}
