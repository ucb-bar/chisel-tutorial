package TutorialSolutions

import Chisel._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Mul extends Mod {
  val io = new Bundle {
    val x   = UFix(INPUT,  4)
    val y   = UFix(INPUT,  4)
    val z   = UFix(OUTPUT, 8)
  }
  val muls = new ArrayBuffer[UFix]()

  // -------------------------------- \\
  // Calculate io.z = io.x * io.y by
  // building filling out muls
  // -------------------------------- \\

  for (i <- 0 until 16)
    for (j <- 0 until 16)
      muls += UFix(i * j, width = 8)
  val tbl = Vec(muls)
  io.z := tbl((io.x << UFix(4)) | io.y)

  // -------------------------------- \\
}

class MulTests(c: Mul) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val maxInt  = 1 << 4
    for (i <- 0 until 10) {
      val x = rnd.nextInt(maxInt)
      val y = rnd.nextInt(maxInt)
      vars(c.io.x) = UFix(x)
      vars(c.io.y) = UFix(y)
      vars(c.io.z) = UFix(x * y)
      allGood = step(vars) && allGood
    }
    allGood
  }
}
