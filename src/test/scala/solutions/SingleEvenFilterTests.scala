// See LICENSE.txt for license details.
package solutions

import chisel3._
import chisel3.iotesters.PeekPokeTester

class SingleEvenFilterTests[T <: UInt](c: SingleEvenFilter[T]) extends PeekPokeTester(c) {
  val maxInt  = 1 << 16
  for (i <- 0 until 10) {
    val in = rnd.nextInt(maxInt)
    poke(c.io.in.valid, 1)
    poke(c.io.in.bits, in)
    val isSingleEven = (in <= 9) && (in%2 == 1)
    step(1)
    expect(c.io.out.valid, if (isSingleEven) 1 else 0)
    expect(c.io.out.bits, in)
  }
}
