package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class Accumulator extends Module {
  val io = new Bundle {
    val in  = UInt(width = 1, dir = INPUT)
    val out = UInt(width = 8, dir = OUTPUT)
  }
  // COUNT INCOMING TRUES
  // FILL IN HERE ...
  val accumulator = RegReset(UInt(0, 8))
  accumulator := accumulator + io.in
  io.out := accumulator
}

class AccumulatorTests(c: Accumulator) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    var tot     = 0
    for (t <- 0 until 16) {
      vars.clear()
      val in         = rnd.nextInt(2) == 1
      vars(c.io.in)  = Bool(in)
      vars(c.io.out) = UInt(tot)
      allGood        = step(vars) && allGood
      if (in) tot += 1
    }
    allGood
  }
}
