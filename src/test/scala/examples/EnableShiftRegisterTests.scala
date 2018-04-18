// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class EnableShiftRegisterTests(c: EnableShiftRegister) extends PeekPokeTester(c) {
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

class EnableShiftRegisterTester extends ChiselFlatSpec {
  behavior of "EnableShiftRegister"
  backends foreach {backend =>
    it should s"create a pipeline of registers and shift them each cycle in $backend" in {
      Driver(() => new EnableShiftRegister, backend)(c => new EnableShiftRegisterTests(c)) should be (true)
    }
  }
}
