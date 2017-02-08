// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.PeekPokeTester

class Mux2Tests(c: Mux2) extends PeekPokeTester(c) {
  for (s <- 0 until 2) {
    for (i0 <- 0 until 2) {
      for (i1 <- 0 until 2) {
        poke(c.io.sel, s)
        poke(c.io.in1, i1)
        poke(c.io.in0, i0)
        step(1)
        expect(c.io.out, if (s == 1) i1 else i0)
      }
    }
  }
}
