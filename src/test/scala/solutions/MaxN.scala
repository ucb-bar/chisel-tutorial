package solutions

import Chisel.iotesters.{ Backend => TesterBackend, _ }

class MaxNTests(c: MaxN, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
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
