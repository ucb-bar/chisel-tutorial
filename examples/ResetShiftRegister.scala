package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class ResetShiftRegister extends Module {
  val io = new Bundle {
    val in     = UInt(INPUT, 4)
    val shift = Bool(INPUT)
    val out    = UInt(OUTPUT, 4)
  }
  // Register reset to zero
  val r0 = Reg(init = UInt(0, width = 4))
  val r1 = Reg(init = UInt(0, width = 4))
  val r2 = Reg(init = UInt(0, width = 4))
  val r3 = Reg(init = UInt(0, width = 4))
  when (io.shift) {
    r0 := io.in
    r1 := r0
    r2 := r1
    r3 := r2
  }
  io.out := r3
}

class ResetShiftRegisterTests(c: ResetShiftRegister) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val ins     = Array.fill(5){ 0 }
    var k       = 0
    for (t <- 0 until 16) {
      vars.clear()
      val in           = rnd.nextInt(2)
      val shift        = rnd.nextInt(2) == 0
      if (shift) 
        ins(k % 5)     = in
      vars(c.io.in)    = UInt(in)
      vars(c.io.shift) = Bool(shift)
      vars(c.io.out)   = UInt(if (t < 4) 0 else ins((k + 1) % 5))
      allGood          = step(vars) && allGood
      if (shift)
        k = k + 1
    }
    allGood
  }
}
