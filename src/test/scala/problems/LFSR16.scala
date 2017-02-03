// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

class LFSR16Tests(c: LFSR16) extends PeekPokeTester(c) {
  var res = 1
  for (t <- 0 until 16) {
    val inc = rnd.nextInt(2)
    poke(c.io.inc, inc)
    step(1)
    if (inc == 1) {
      val bit = ((res >> 0) ^ (res >> 2) ^ (res >> 3) ^ (res >> 5) ) & 1
      res = (res >> 1) | (bit << 15)
    }
    expect(c.io.out, res)
  }
}
