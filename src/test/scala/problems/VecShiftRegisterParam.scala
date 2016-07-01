// See LICENSE.txt for license details.
package problems

import Chisel.iotesters.{ Backend => TesterBackend, _ }

class VecShiftRegisterParamTests(c: VecShiftRegisterParam, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  val reg = Array.fill(c.n){ 0 }
  for (t <- 0 until 16) {
    val in = rnd.nextInt(1 << c.w)
    poke(c.io.in, in)
    step(1)
    for (i <- c.n-1 to 1 by -1)
      reg(i) = reg(i-1)
    reg(0) = in
    expect(c.io.out, reg(c.n-1))
  }
}
