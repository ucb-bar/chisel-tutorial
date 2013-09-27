package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class EnableShiftRegister extends Module {
  val io = new Bundle {
    val in     = UInt(INPUT, 1)
    val enable = Bool(INPUT)
    val out    = UInt(OUTPUT, 1)
  }
  val r0 = Reg(UInt())
  val r1 = Reg(UInt())
  val r2 = Reg(UInt())
  val r3 = Reg(UInt())
  when(reset) {
    r0 := UInt(0)
    r1 := UInt(0)
    r2 := UInt(0)
    r3 := UInt(0)
  } .elsewhen(io.enable) {
    r0 := io.in
    r1 := r0
    r2 := r1
    r3 := r2
  }
  io.out := r3
}

class EnableShiftRegisterTests(c: EnableShiftRegister) extends Tester(c, Array(c.io)) {  
  defTests {
    val allGood = true
    allGood
  }
}
