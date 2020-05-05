// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.PeekPokeTester

class VecShiftRegisterSimpleTests(c: VecShiftRegisterSimple) extends PeekPokeTester(c) {
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
