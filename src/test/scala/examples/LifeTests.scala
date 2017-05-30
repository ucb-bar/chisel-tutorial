// See LICENSE.txt for license details.
package examples


import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}
import util.Random

class LifeTests(c: Life) extends PeekPokeTester(c) {
  for (t <- 0 until 16) {
    step(1)
    for (j <- 0 until c.n) {
      for (i <- 0 until c.n) {
        peek(c.io.state(c.idx(i, j)))
      }
      println()
    }
  }
}

class LifeTester extends ChiselFlatSpec {
  behavior of "Life"
  backends foreach {backend =>
    it should s"implement transition rules for Conway's life game in $backend" in {
      Driver(() => new Life(3), backend)((c) => new LifeTests(c)) should be (true)
    }
  }
}

