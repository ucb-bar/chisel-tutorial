package TutorialProblems

import scala.collection.mutable.HashMap
import scala.util.Random

import Chisel._ 

class MaxN(val n: Int, val w: Int) extends Module {

  private def Max2(x: UInt, y: UInt) = Mux(x > y, x, y)

  val io = new Bundle {
    val ins = Vec.fill(n){ UInt(INPUT, w) }
    val out = UInt(OUTPUT, w)
  }
  io.out := io.ins.reduceLeft(Max2)
}

class MaxNTests(c: MaxN) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    for (i <- 0 until 10) {
      vars.clear()
      var mx = 0
      for (i <- 0 until c.n) {
        // FILL THIS IN HERE
        vars(c.io.ins(0)) = UInt(0)
      }
      vars(c.io.out) = UInt(1)  // FILL THIS IN HERE
      allGood = step(vars) && allGood
    }
    allGood
  }
}

