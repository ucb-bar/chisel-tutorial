package examples

import Chisel._
import Chisel.iotesters._

class Adder4Tests(c: Adder4, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val rnd2 = rnd.nextInt(2)
  for (t <- 0 until 4) {
    val rnd0 = rnd.nextInt(16)
    val rnd1 = rnd.nextInt(16)
    val rnd2 = rnd.nextInt(2)
    poke(c.io.A,   rnd0)
    poke(c.io.B,   rnd1)
    poke(c.io.Cin, rnd2)
    step(1)
    val rsum = (rnd0 & 0xF) + (rnd1 & 0xF) + (rnd2 & 0x1)
    expect(c.io.Sum, (rsum & 0xF))
    expect(c.io.Cout, rsum >> 4)
  }
}
