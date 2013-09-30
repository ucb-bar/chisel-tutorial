package TutorialSolutions

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class LFSR16 extends Module {
  val io = new Bundle {
    val inc = Bool(INPUT)
    val out = UInt(OUTPUT, 16)
  }
  val res = Reg(init = UInt(1, 16))
  when (io.inc) { 
    res := Cat(res(0)^res(2)^res(3)^res(5), res(15,1)) 
  }
  io.out := res
}

class LFSR16Tests(c: LFSR16) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    var res     = 1
    for (t <- 0 until 16) {
      vars.clear()
      val inc        = rnd.nextInt(2) == 1
      vars(c.io.inc) = Bool(inc)
      vars(c.io.out) = UInt(res)
      if (inc) {
        val bit = ((res >> 0) ^ (res >> 2) ^ (res >> 3) ^ (res >> 5) ) & 1;
        res = (res >> 1) | (bit << 15);
      }
      allGood  = step(vars) && allGood
    }
    allGood
  }
}
