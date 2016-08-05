// See LICENSE.txt for license details.
package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }
import util.Random

class LifeTests(c: Life, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
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
      runPeekPokeTester(() => new Life(3), backend) {
        (c,b) => new LifeTests(c,b)} should be (true)
    }
  }
}

