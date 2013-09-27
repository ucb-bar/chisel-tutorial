package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class ResetShiftRegister extends Module {
  val io = new Bundle {
    val in     = UInt(INPUT, 1)
    val enable = Bool(INPUT)
    val out    = UInt(OUTPUT, 1)
  }
  // Register reset to zero
  val r0 = Reg(init = UInt(0, width = 1))
  val r1 = Reg(init = UInt(0, width = 1))
  val r2 = Reg(init = UInt(0, width = 1))
  val r3 = Reg(init = UInt(0, width = 1))
  when (io.enable) {
    r0 := io.in
    r1 := r0
    r2 := r1
    r3 := r2
  }
  io.out := r3
}

class ResetShiftRegisterTests(c: ResetShiftRegister) extends Tester(c, Array(c.io)) {  
  defTests {
    val allGood = true
    allGood
  }
}
