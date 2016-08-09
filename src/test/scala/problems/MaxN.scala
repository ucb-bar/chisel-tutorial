// See LICENSE.txt for license details.
package problems

import Chisel.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class MaxNTests(c: MaxN) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {
    var mx = 0
    for (i <- 0 until c.n) {
      // FILL THIS IN HERE
      poke(c.io.ins(0), 0)
    }
    step(1)
    // FILL THIS IN HERE
    expect(c.io.out, 1)
  }
}
