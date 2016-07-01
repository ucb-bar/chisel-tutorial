// See LICENSE.txt for license details.
package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }

class FunctionalityTests(c: Functionality, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
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
  "Functionality" should "demonstrate usage of functions that generate code" in {
    runPeekPokeTester(() => new Functionality) {
      (c,b) => new FunctionalityTests(c,b)
    }
  }
}
