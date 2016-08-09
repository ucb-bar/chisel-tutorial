// See LICENSE.txt for license details.
package problems

import Chisel.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {
    // FILL THIS IN HERE
    poke(c.io.in0, 0)
    poke(c.io.in1, 0)
    // FILL THIS IN HERE
    step(1)
    expect(c.io.out, 1)
  }
}
