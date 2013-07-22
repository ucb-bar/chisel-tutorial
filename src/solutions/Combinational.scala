package TutorialSolutions

import Chisel._
import scala.collection.mutable.HashMap
import scala.util.Random

class Combinational extends Module {
  val io = new Bundle {
    val x   = UInt(INPUT,  16)
    val y   = UInt(INPUT,  16)
    val z   = UInt(OUTPUT, 16)
  }
  io.z := io.x + io.y
}

class CombinationalTests(c: Combinational) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val maxInt  = 1 << 16
    for (i <- 0 until 10) {
      vars.clear()
      val x = rnd.nextInt(maxInt)
      val y = rnd.nextInt(maxInt)
      vars(c.io.x) = UInt(x)
      vars(c.io.y) = UInt(y)
      vars(c.io.z) = UInt((x + y)&(maxInt-1))
      allGood = step(vars) && allGood
    }
    allGood
  }
}

