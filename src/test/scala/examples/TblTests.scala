// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class TblTests(c: Tbl) extends PeekPokeTester(c) {
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
      Driver(() => new Tbl, backend)((c) => new TblTests(c)) should be (true)
    }
  }
}


