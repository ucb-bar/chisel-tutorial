// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class GCDTests(c: GCD) extends PeekPokeTester(c) {
  val (a, b, z) = (64, 48, 16)
  do {
    val first = if (t == 0) 1 else 0;
    poke(c.io.a, a)
    poke(c.io.b, b)
    poke(c.io.e, first)
    step(1)
  } while (t <= 1 || peek(c.io.v) == 0)
  expect(c.io.z, z)
}

class GCDTester extends ChiselFlatSpec {
  behavior of "GCD"
  backends foreach { backend =>
    it should s"correctly compute GCD of two numbers in $backend" in {
      Driver(() => new GCD, backend)(c => new GCDTests(c)) should be (true)
    }
  }
}
