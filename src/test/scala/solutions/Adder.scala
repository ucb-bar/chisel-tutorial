package solutions

import Chisel.iotesters.{ Backend => TesterBackend, _ }

class AdderTests(c: Adder, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
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
  "Adder" should "correctly add randomly generated numbers" in {
    runPeekPokeTester(() => new Adder(16)){
      (c,b) => new AdderTests(c,b)}
  }
}
