package TutorialProblems

import Chisel._

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  w)
    val out = UInt(OUTPUT, w)
  }
  /// fill in here ...
  io.out := UInt(0)
}

class VecShiftRegisterParamTests(c: VecShiftRegisterParam) extends Tester(c) { 
  val reg = Array.fill(c.n){ 0 }
  for (t <- 0 until 16) {
    val in = rnd.nextInt(1 << c.w)
    poke(c.io.in, in)
    step(1)
    expect(c.io.out, reg(c.n-1))
    for (i <- c.n-1 to 1 by -1)
      reg(i) = reg(i-1)
    reg(0) = in
  }
}
