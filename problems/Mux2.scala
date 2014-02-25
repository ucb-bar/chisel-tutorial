package TutorialProblems

import Chisel._
import scala.math._

class Mux2 extends Module {
  val io = new Bundle {
    val sel = Bits(INPUT,  1)
    val in0 = Bits(INPUT,  1)
    val in1 = Bits(INPUT,  1)
    val out = Bits(OUTPUT, 1)
  }
  io.out := (io.sel & io.in1) | (~io.sel & io.in0)
}

class Mux2Tests(c: Mux2) extends Testy(c) {
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
