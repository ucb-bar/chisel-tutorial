// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class ResetShiftRegisterTests(c: ResetShiftRegister) extends PeekPokeTester(c) {
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

class ResetShiftRegisterTester extends ChiselFlatSpec {
  behavior of "ResetShiftRegister"
  backends foreach {backend =>
    it should s"correctly compute ResetShiftRegister of two numbers in $backend" in {
      Driver(() => new ResetShiftRegister, backend) {
        (c) => new ResetShiftRegisterTests(c)} should be (true)
    }
  }
}
