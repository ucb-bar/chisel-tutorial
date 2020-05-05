// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

class AccumulatorTests(c: Accumulator) extends PeekPokeTester(c) {
  var tot = 0
  for (t <- 0 until 16) {
    val in = rnd.nextInt(2)
    poke(c.io.in, in)
    step(1)
    if (in == 1) tot += 1
    expect(c.io.out, tot)
  }
}
