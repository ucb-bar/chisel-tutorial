// See LICENSE.txt for license details.
package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }
import util.Random

class LifeTests(c: Life, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  for (t <- 0 until 16) {
    step(1)
    for (j <- 0 until c.n) {
      for (i <- 0 until c.n) {
        print(peek(c.io.state(c.idx(i, j))))
      }
      println()
    }
  }
}

class LifeTester extends ChiselFlatSpec {
  "Life" should "implement transition rules for Conway's life game" in {
    runPeekPokeTester(() => new Life(3)) {
      (c,b) => new LifeTests(c,b)
    }
  }
}

