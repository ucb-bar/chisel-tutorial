// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class AdderTests(c: Adder) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {
    val in0 = rnd.nextInt(1 << c.w)
    val in1 = rnd.nextInt(1 << c.w)
    poke(c.io.in0, in0)
    poke(c.io.in1, in1)
    step(1)
    expect(c.io.out, (in0 + in1)&((1 << c.w)-1))
  }
}

class AdderTester extends ChiselFlatSpec {
  behavior of "Adder"
  backends foreach {backend =>
    it should s"correctly add randomly generated numbers in $backend" in {
      Driver(() => new Adder(16), backend)(c => new AdderTests(c)) should be (true)
    }
  }
}
