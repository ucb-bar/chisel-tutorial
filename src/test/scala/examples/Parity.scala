package examples

import Chisel._
import Chisel.iotesters._

class ParityTests(c: Parity, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  var isOdd = 0
  for (t <- 0 until 10) {
    val bit = rnd.nextInt(2)
    poke(c.io.in, bit)
    step(1)
    isOdd = (isOdd + bit) % 2;
    expect(c.io.out, isOdd)
  }
}

