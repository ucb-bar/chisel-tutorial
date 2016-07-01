// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._

class Tbl extends Module {
  val io = new Bundle {
    val addr = UInt(INPUT,  8)
    val out  = UInt(OUTPUT, 8)
  }
  val r = Wire(init = Vec(Range(0, 256).map(UInt(_, width = 8))))
  io.out := r(io.addr)
}

class TblTests(c: Tbl) extends Tester(c) {
  for (t <- 0 until 16) {
    val addr = rnd.nextInt(256)
    poke(c.io.addr, addr)
    step(1)
    expect(c.io.out, addr)
  }
}

