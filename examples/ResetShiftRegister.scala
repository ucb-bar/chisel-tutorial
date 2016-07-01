// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._

class ResetShiftRegister extends Module {
  val io = new Bundle {
    val in    = UInt(INPUT, 4)
    val shift = Bool(INPUT)
    val out   = UInt(OUTPUT, 4)
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

class ResetShiftRegisterTests(c: ResetShiftRegister) extends Tester(c) {
  val ins = Array.fill(4){ 0 }
  val regs = Array.fill(4){ 0 }
  var k   = 0
  for (n <- 0 until 16) {
    val in    =  rnd.nextInt(16)
    val shift =  rnd.nextInt(2)
    poke(c.io.in,    in)
    poke(c.io.shift, shift)
    step(1)
    if (shift == 1) {
      ins(k % 4) = in
      k = k + 1
    }
    expect(c.io.out, (if (k < 4) 0 else ins(k % 4)))
  }
}
