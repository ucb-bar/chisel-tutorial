package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class EnableShiftRegister extends Module {
  val io = new Bundle {
    val in    = UInt(INPUT, 4)
    val shift = Bool(INPUT)
    val out   = UInt(OUTPUT, 4)
  }
  val r0 = Reg(UInt())
  val r1 = Reg(UInt())
  val r2 = Reg(UInt())
  val r3 = Reg(UInt())
  when(reset) {
    r0 := UInt(0, 4)
    r1 := UInt(0, 4)
    r2 := UInt(0, 4)
    r3 := UInt(0, 4)
  } .elsewhen(io.shift) {
    r0 := io.in
    r1 := r0
    r2 := r1
    r3 := r2
  }
  io.out := r3
}

class EnableShiftRegisterTests(c: EnableShiftRegister) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val reg     = Array.fill(4){ 0 }
    for (t <- 0 until 16) {
      vars.clear()
      val in           = rnd.nextInt(2)
      val shift        = rnd.nextInt(2) == 0
      vars(c.io.in)    = UInt(in)
      vars(c.io.shift) = Bool(shift)
      vars(c.io.out)   = UInt(reg(3))
      allGood          = step(vars) && allGood
      if (shift) {
        for (i <- 3 to 1 by -1)
          reg(i) = reg(i-1)
        reg(0) = in
      }
    }
    allGood
  }
}
