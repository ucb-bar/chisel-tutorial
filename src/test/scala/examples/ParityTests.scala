// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class ParityTests(c: Parity) extends PeekPokeTester(c) {
  var isOdd = 0
  for (t <- 0 until 10) {
    val bit = rnd.nextInt(2)
    poke(c.io.in, bit)
    step(1)
    isOdd = (isOdd + bit) % 2;
    expect(c.io.out, isOdd)
  }
}

class ParityTester extends ChiselFlatSpec {
  behavior of "Parity"
  backends foreach {backend =>
    it should s"correctly compute parity of two numbers $backend" in {
      Driver(() => new Parity, backend) {
        (c) => new ParityTests(c)} should be (true)
    }
  }
}


