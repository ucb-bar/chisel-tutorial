// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class HiLoMultiplierTests(c: HiLoMultiplier) extends PeekPokeTester(c) {
  for (t <- 0 until 4) {
    val rnd0 = rnd.nextInt(65535)
    val rnd1 = rnd.nextInt(65535)
    val ref_out = rnd0 * rnd1
    poke(c.io.A, rnd0)
    poke(c.io.B, rnd1)
    step(1)
    expect(c.io.Lo, ref_out & BigInt("ffff", 16))
    expect(c.io.Hi, (ref_out & BigInt("ffff0000", 16)) >> 16)
  }
}

class HiLoMultiplierTester extends ChiselFlatSpec {
  behavior of "HiLoMultiplier"
  backends foreach {backend =>
    it should s"multiply two numbers returning result as a hi and low output $backend" in {
      Driver(() => new HiLoMultiplier, backend)((c) => new HiLoMultiplierTests(c)) should be (true)
    }
  }
}
