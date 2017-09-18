// See LICENSE.txt for license details.

package utils

import scala.collection.mutable.ArrayBuffer
import scala.util.Properties.envOrElse

object TutorialRunner {
  def apply(tutorialMap: Map[String, Array[String] => Boolean], args: Array[String]): Unit = {
    val problemsToRun = if(args.isEmpty || args.head == "all" ) {
      tutorialMap.keys.toSeq.sorted.toArray
    }
    else {
      args.takeWhile(!_.startsWith("-"))
    }

    val driverArgs = args.dropWhile(!_.startsWith("-"))
    var successful = 0
    val errors = new ArrayBuffer[String]
    for(testName <- problemsToRun) {
      tutorialMap.get(testName) match {
        case Some(test) =>
          println(s"Starting tutorial $testName")
          try {
            if(test(driverArgs)) {
              successful += 1
            }
            else {
              errors += s"Tutorial $testName: test error occurred"
            }
          }
          catch {
            case exception: Exception =>
              exception.printStackTrace()
              errors += s"Tutorial $testName: exception ${exception.getMessage}"
            case t : Throwable =>
              errors += s"Tutorial $testName: throwable ${t.getMessage}"
          }
        case _ =>
          errors += s"Bad tutorial name: $testName"
      }

    }
    if(successful > 0) {
      println(s"Tutorials passing: $successful")
    }
    if(errors.nonEmpty) {
      println("=" * 80)
      println(s"Errors: ${errors.length}: in the following tutorials")
      println(errors.mkString("\n"))
      println("=" * 80)
      System.exit(1)
    }
  }
}
