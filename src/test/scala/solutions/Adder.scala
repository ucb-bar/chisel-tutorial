// See LICENSE.txt for license details.
package solutions

import Chisel.iotesters.{ Backend => TesterBackend, _ }

class AdderTests(c: AdderSol, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
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
  behavior of "AdderSol"
  backends foreach {backend =>
    it should s"correctly add randomly generated numbers in $backend" in {
      runPeekPokeTester(() => new AdderSol(16), backend){
        (c,b) => new AdderTests(c,b)} should be (true)
    }
  }
}
