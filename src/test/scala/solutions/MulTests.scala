// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.PeekPokeTester

class MulTests(c: Mul) extends PeekPokeTester(c) {
  val maxInt  = 1 << 4
  for (i <- 0 until 10) {
    val x = rnd.nextInt(maxInt)
    val y = rnd.nextInt(maxInt)
    poke(c.io.x, x)
    poke(c.io.y, y)
    step(1)
    expect(c.io.z, x * y)
  }
}
