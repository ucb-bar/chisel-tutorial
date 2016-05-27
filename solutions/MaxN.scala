package TutorialSolutions

import Chisel._
import Chisel.iotesters._

class MaxN(val n: Int, val w: Int) extends Module {

  private def Max2(x: UInt, y: UInt) = Mux(x > y, x, y)

  val io = new Bundle {
    val ins = Vec(n, UInt(INPUT, w))
    val out = UInt(OUTPUT, w)
  }
  io.out := io.ins.reduceLeft(Max2)
}

class MaxNTests(c: MaxN, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val ins = Array.fill(c.n){ 0 }
  for (i <- 0 until 10) {
    var mx = 0
    for (i <- 0 until c.n) {
      ins(i) = rnd.nextInt(1 << c.w)
      poke(c.io.ins(i), ins(i))
      mx = if (ins(i) > mx) ins(i) else mx;
    }
    step(1)
    expect(c.io.out, mx)
  }
}
