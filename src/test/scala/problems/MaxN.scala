package problems

import Chisel._
import Chisel.iotesters._

class MaxN(val n: Int, val w: Int) extends Module {

  private def Max2(x: UInt, y: UInt) = Mux(x > y, x, y)

  val io = new Bundle {
    val ins = Vec(n,  UInt(INPUT, w) )
    val out = UInt(OUTPUT, w)
  }
  io.out := io.ins.reduceLeft(Max2)
}

class MaxNTests(c: MaxN, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  for (i <- 0 until 10) {
    var mx = 0
    for (i <- 0 until c.n) {
      // FILL THIS IN HERE
      poke(c.io.ins(0), 0)
    }
    step(1)
    // FILL THIS IN HERE
    expect(c.io.out, 1)
  }
}
