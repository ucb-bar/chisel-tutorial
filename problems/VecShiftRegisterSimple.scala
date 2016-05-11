package TutorialProblems

import Chisel._
import Chisel.iotesters._

class VecShiftRegisterSimple extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  8)
    val out = UInt(OUTPUT, 8)
  }
  val delays = Reg(init = Vec.fill(4)(UInt(0, width = 8)))
  /// fill in here ...
  io.out := UInt(0)
}

class VecShiftRegisterSimpleTests(c: VecShiftRegisterSimple) extends ClassicTester(c) {
  val reg = Array.fill(4){ 0 }
  for (t <- 0 until 16) {
    val in = rnd.nextInt(256)
    poke(c.io.in, in)
    step(1)
    for (i <- 3 to 1 by -1)
      reg(i) = reg(i-1)
    reg(0) = in
    expect(c.io.out, reg(3))
  }
}
