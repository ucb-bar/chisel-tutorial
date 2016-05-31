package examples

import Chisel._
import Chisel.iotesters._

class ByteSelectorTests(c: ByteSelector, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val test_in = 12345678
  for (t <- 0 until 4) {
    poke(c.io.in,     test_in)
    poke(c.io.offset, t)
    step(1)
    expect(c.io.out, (test_in >> (t * 8)) & 0xFF)
  }
}
