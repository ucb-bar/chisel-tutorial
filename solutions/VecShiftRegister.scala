package TutorialSolutions

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class VecShiftRegister extends Module {
  val io = new Bundle {
    val ins   = Vec.fill(4){ UInt(INPUT, 4) }
    val load  = Bool(INPUT)
    val shift = Bool(INPUT)
    val out   = UInt(OUTPUT, 4)
  }
  val delays = Vec.fill(4){ Reg(UInt()) }
  when (io.load) {
    delays(0) := io.ins(0)
    delays(1) := io.ins(1)
    delays(2) := io.ins(2)
    delays(3) := io.ins(3)
  } .elsewhen(io.shift) {
    delays(0) := io.ins(0)
    delays(1) := delays(0)
    delays(2) := delays(1)
    delays(3) := delays(2)
  }
  io.out := delays(3)
}

class VecShiftRegisterTests(c: VecShiftRegister) extends Tester(c, Array(c.io)) {    defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val reg     = Array.fill(4){ 0 }
    val ins     = Array.fill(4){ 0 }
    for (t <- 0 until 16) {
      vars.clear()
      for (i <- 0 until 4)
        ins(i) = rnd.nextInt(16)
      val shift = rnd.nextInt(2) == 1
      val load  = rnd.nextInt(2) == 1
      for (i <- 0 until 4)
        vars(c.io.ins(i)) = UInt(ins(i))
      vars(c.io.load)  = Bool(load)
      vars(c.io.shift) = Bool(shift)
      vars(c.io.out)   = UInt(reg(3))
      if (load) {
        for (i <- 0 until 4) 
          reg(i) = ins(i)
      } else if (shift) {
        for (i <- 3 to 1 by -1)
          reg(i) = reg(i-1)
        reg(0) = ins(0)
      }
      allGood = step(vars) && allGood
    }
    allGood
  }
}
