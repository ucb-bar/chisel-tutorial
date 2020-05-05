// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class AdderTests(c: Adder) extends PeekPokeTester(c) {
  for (t <- 0 until (1 << (c.n + 1))) {
    val rnd0 = rnd.nextInt(1 << c.n)
    val rnd1 = rnd.nextInt(1 << c.n)
    val rnd2 = rnd.nextInt(2)

    poke(c.io.A, rnd0)
    poke(c.io.B, rnd1)
    poke(c.io.Cin, rnd2)
    step(1)
    val rsum = rnd0 + rnd1 + rnd2
    val mask = BigInt("1"*c.n, 2)

    expect(c.io.Sum, rsum &  mask)
    expect(c.io.Cout,  ((1 << c.n) & rsum) >> c.n)
  }
}

class AdderTester extends ChiselFlatSpec {
  behavior of "Adder"
  backends foreach {backend =>
    it should s"correctly add randomly generated numbers $backend" in {
      Driver(() => new Adder(8))(c => new AdderTests(c)) should be (true)
    }
  }
}
