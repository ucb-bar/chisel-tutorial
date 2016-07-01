// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._

class ShiftRegister extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  1)
    val out = UInt(OUTPUT, 1)
  }
  val r0 = Reg(next = io.in)
  val r1 = Reg(next = r0)
  val r2 = Reg(next = r1)
  val r3 = Reg(next = r2)
  io.out := r3
}

class ShiftRegisterTests(c: ShiftRegister) extends Tester(c) {  
  val reg     = Array.fill(4){ 0 }
  for (t <- 0 until 64) {
    val in = rnd.nextInt(2)
    poke(c.io.in, in)
    step(1)
    for (i <- 3 to 1 by -1)
      reg(i) = reg(i-1)
    reg(0) = in
    if (t >= 4) expect(c.io.out, reg(3))
  }
}
