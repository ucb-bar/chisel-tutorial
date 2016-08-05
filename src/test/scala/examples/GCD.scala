// See LICENSE.txt for license details.
package examples

import Chisel.iotesters.{ Backend => TesterBackend, _ }

class GCDTests(c: GCD, backend: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=backend) {
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
      runPeekPokeTester(() => new GCD, backend) {
        (c,b) => new GCDTests(c,b)} should be (true)
    }
  }
}
