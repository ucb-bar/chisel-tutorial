// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class FunctionalityTests(c: Functionality) extends PeekPokeTester(c) {
  val maxInt = 1 << 16
  for (i <- 0 until 10) {
    val x = rnd.nextInt(maxInt)
    val y = rnd.nextInt(maxInt)
    poke(c.io.x, x)
    poke(c.io.y, y)
    step(1)
    expect(c.io.z, (x & y) | (~x & y))
  }
}

class FunctionalityTester extends ChiselFlatSpec {
  behavior of "Functionality"
  backends foreach {backend =>
    it should s"demonstrate usage of functions that generate code in $backend" in {
      Driver(() => new Functionality, backend)((c) => new FunctionalityTests(c)) should be (true)
    }
  }
}
