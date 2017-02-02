// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

// Problem:
//
// Implement test with PeekPokeTester
//
class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {

    // Implement below ----------

    poke(c.io.in0, 0)
    poke(c.io.in1, 0)
    step(1)
    expect(c.io.out, 1)

    // Implement above ----------
  }
}
