package examples

import Chisel._
import Chisel.iotesters._

class TblTests(c: Tbl, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  for (t <- 0 until 16) {
    val addr = rnd.nextInt(256)
    poke(c.io.addr, addr)
    step(1)
    expect(c.io.out, addr)
  }
}

