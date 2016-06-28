package problems

import Chisel.iotesters.{ Backend => TesterBackend, _ }
import scala.math._

class Mux2Tests(c: Mux2, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  val n = pow(2, 3).toInt
  for (s <- 0 until 2) {
    for (i0 <- 0 until 2) {
      for (i1 <- 0 until 2) {
        poke(c.io.sel, s)
        poke(c.io.in1, i1)
        poke(c.io.in0, i0)
        step(1)
        expect(c.io.out, (if (s == 1) i1 else i0))
      }
    }
  }
}
