package TutorialSolutions

import Chisel._
import scala.collection.mutable.HashMap
import util.Random

class Tbl extends Module {
  val io = new Bundle {
    val addr = UInt(INPUT,  8)
    val out  = UInt(OUTPUT, 8)
  }
  val r = Vec(Range(0, 256).map(UInt(_, width = 8)))
  io.out := r(io.addr)
}

class TblTests(c: Tbl) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    for (t <- 0 until 16) {
      vars.clear()
      val addr        = rnd.nextInt(256)
      vars(c.io.addr) = UInt(addr)
      vars(c.io.out)  = UInt(addr)
      allGood         = step(vars) && allGood
    }
    allGood
  }
}

