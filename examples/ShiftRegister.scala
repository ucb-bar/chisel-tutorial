package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class ShiftRegister extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  1)
    val out = UInt(OUTPUT, 1)
  }
  val r0 = Reg(next = io.in)
  val r1 = Reg(next = r0)
  val r2 = Reg(next = r1)
  val r3 = Reg(next = r2)
  io.out := r3
}

class ShiftRegisterTests(c: ShiftRegister) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val reg     = Array.fill(4){ 0 }
    for (t <- 0 until 64) {
      vars.clear()
      val in         = rnd.nextInt(2)
      vars(c.io.in)  = UInt(in)
      vars(c.io.out) = UInt(reg(3))
      for (i <- 3 to 1 by -1)
        reg(i) = reg(i-1)
      reg(0) = in
      allGood = (step(vars) || t < 4) && allGood
    }
    allGood
  }
}
