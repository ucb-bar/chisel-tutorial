package examples

import Chisel._
import Chisel.iotesters._
import util.Random

class LifeTests(c: Life, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
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
