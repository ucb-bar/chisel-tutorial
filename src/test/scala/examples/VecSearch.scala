package examples

import Chisel._
import Chisel.iotesters._

class VecSearchTests(c: VecSearch, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val list = VecSearchTest.pattern
  for (elt <- list) {
    expect(c.io.out, elt)
    step(1)
  }
}
