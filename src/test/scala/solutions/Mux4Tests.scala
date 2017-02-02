// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.PeekPokeTester

class Mux4Tests(c: Mux4) extends PeekPokeTester(c) {
  for (s0 <- 0 until 2) {
    for (s1 <- 0 until 2) {
      for(i0 <- 0 until 2) {
        for(i1 <- 0 until 2) {
          for(i2 <- 0 until 2) {
            for(i3 <- 0 until 2) {
              poke(c.io.sel, s1 << 1 | s0)
              poke(c.io.in0, i0)
              poke(c.io.in1, i1)
              poke(c.io.in2, i2)
              poke(c.io.in3, i3)
              step(1)
              val out = if(s1 == 1) {
                          if (s0 == 1) i3 else i2
                        } else {
                          if (s0 == 1) i1 else i0 
                        }
              expect(c.io.out, out)
            }
          }
        }
      } 
    }
  }
}
