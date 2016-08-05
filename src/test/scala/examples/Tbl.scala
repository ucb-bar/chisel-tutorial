// See LICENSE.txt for license details.
package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }

class TblTests(c: Tbl, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  for (t <- 0 until 16) {
    val addr = rnd.nextInt(256)
    poke(c.io.addr, addr)
    step(1)
    expect(c.io.out, addr)
  }
}

class TblTester extends ChiselFlatSpec {
  behavior of "Tbl"
  backends foreach {backend =>
    it should s"implement a table of numbers in $backend" in {
      runPeekPokeTester(() => new Tbl, backend) {
        (c,b) => new TblTests(c,b)} should be (true)
    }
  }
}


