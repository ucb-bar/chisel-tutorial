// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.PeekPokeTester

// Problem:
//
// Implement test for MaxN using PeekPokeTester
//
class MaxNTests(c: MaxN) extends PeekPokeTester(c) {
  val ins = Array.fill(c.n){ 0 }
  for (i <- 0 until 10) {
    var mx = 0
    for (i <- 0 until c.n) {
      ins(i) = rnd.nextInt(1 << c.w)
      poke(c.io.ins(i), ins(i))
      mx = if (ins(i) > mx) ins(i) else mx
    }
    step(1)
    expect(c.io.out, mx)
  }
}
