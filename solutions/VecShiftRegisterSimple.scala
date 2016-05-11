package TutorialSolutions

import Chisel._
import Chisel.iotesters._

class VecShiftRegisterSimple extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  8)
    val out = UInt(OUTPUT, 8)
  }
  val delays = Reg(init = Vec.fill(4)(UInt(0, width = 8)))
  delays(0) := io.in
  delays(1) := delays(0)
  delays(2) := delays(1)
  delays(3) := delays(2)
  io.out    := delays(3)
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
