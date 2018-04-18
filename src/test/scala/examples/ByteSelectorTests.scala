// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class ByteSelectorTests(c: ByteSelector) extends PeekPokeTester(c) {
  val test_in = 12345678
  for (t <- 0 until 4) {
    poke(c.io.in,     test_in)
    poke(c.io.offset, t)
    step(1)
    expect(c.io.out, (test_in >> (t * 8)) & 0xFF)
  }
}

class ByteSelectorTester extends ChiselFlatSpec {
  behavior of "ByteSelector"
  backends foreach {backend =>
    it should s"correctly select correct bits from an input in $backend" in {
      Driver(() => new ByteSelector, backend)((c) => new ByteSelectorTests(c)) should be (true)
    }
  }
}
