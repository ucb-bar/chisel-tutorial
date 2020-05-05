// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class CombinationalTests(c: Combinational) extends PeekPokeTester(c) {
  val maxInt = 1 << 16
  for (i <- 0 until 10) {
    val x = rnd.nextInt(maxInt)
    val y = rnd.nextInt(maxInt)
    poke(c.io.x, x)
    poke(c.io.y, y)
    step(1)
    expect(c.io.z, (x + y)&(maxInt-1))
  }
}

class CombinationalTester extends ChiselFlatSpec {
  behavior of "Combinational"
  backends foreach {backend =>
    it should s"correctly add randomly generated numbers in $backend" in {
      Driver(() => new Combinational, backend)(c => new CombinationalTests(c)) should be (true)
    }
  }
}

