// See LICENSE for license details.

package utils

import scala.collection.mutable.ArrayBuffer

object TutorialRunner {
  def apply(tutorialMap: Map[String, String => Boolean], args: Array[String]): Unit = {
    val backendName = "firrtl"
    val problemsToRun = if(args.isEmpty || args.head == "all" ) {
      tutorialMap.keys.toSeq.sorted.toArray
    }
    else {
      args
    }

    val errors = new ArrayBuffer[String]
    for(testName <- problemsToRun) {
      tutorialMap.get(testName) match {
        case Some(test) =>
          println(s"running problem $testName")
          try {
            if(! test(backendName)) {
              errors += s"Problem $testName: test error occurred"
            }
          }
          catch {
            case exception: Exception =>
              exception.printStackTrace()
              errors += s"Problem $testName: exception ${exception.getMessage}"
            case t : Throwable =>
              errors += s"Problem $testName: throwable ${t.getMessage}"
          }
        case _ =>
          errors += s"Bad problem name: $testName"
      }

    }
    if(errors.nonEmpty) {
      println(s"${errors.length} errors occurred" + ("=" * 60))
      println(errors.mkString("\n"))
      System.exit(1)
    }
  }
}
