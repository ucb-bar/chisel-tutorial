package TutorialExamples

import Chisel._

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
  counter(r0, r1, r2, r3)
}

class ResetShiftRegisterTests(c: ResetShiftRegister) extends CounterTester(c) {  
  val ins = Array.fill(5){ 0 }
  var k   = 0
  for (t <- 0 until 16) {
    val in    = rnd.nextInt(2)
    val shift = rnd.nextInt(2)
    if (shift == 1) 
      ins(k % 5) = in
    poke(c.io.in,    in)
    poke(c.io.shift, shift)
    step(1)
    expect(c.io.out, (if (t < 4) 0 else ins((k + 1) % 5)))
    if (shift == 1)
      k = k + 1
  }
}
