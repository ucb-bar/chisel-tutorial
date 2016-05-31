package examples

import Chisel._
import Chisel.iotesters._

class EnableShiftRegisterTests(c: EnableShiftRegister, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val reg = Array.fill(4){ 0 }
  for (t <- 0 until 16) {
    val in    = rnd.nextInt(16)
    val shift = rnd.nextInt(2)
    poke(c.io.in,    in)
    poke(c.io.shift, shift)
    step(1)
    if (shift == 1) {
      for (i <- 3 to 1 by -1)
        reg(i) = reg(i-1)
      reg(0) = in
    }
    expect(c.io.out, reg(3))
  }
}
