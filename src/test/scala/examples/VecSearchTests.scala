// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class VecSearchTests(c: VecSearch) extends PeekPokeTester(c) {
  val list = VecSearchTest.pattern
  for (elt <- list) {
    expect(c.io.out, elt)
    step(1)
  }
}

class VecSearchTester extends ChiselFlatSpec {
  behavior of "VecSearch"
  backends foreach {backend =>
    it should s"correctly look for element in vector in $backend" in {
      Driver(() => new VecSearch, backend)((c) => new VecSearchTests(c)) should be (true)
    }
  }
}

