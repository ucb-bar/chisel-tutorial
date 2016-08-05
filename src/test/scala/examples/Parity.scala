// See LICENSE.txt for license details.
package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }

class ParityTests(c: Parity, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
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
      runPeekPokeTester(() => new Parity, backend) {
        (c,b) => new ParityTests(c,b)} should be (true)
    }
  }
}


