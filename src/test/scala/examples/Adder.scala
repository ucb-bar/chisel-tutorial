package examples

import Chisel.iotesters._

class AdderTests(c: Adder, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  for (t <- 0 until 4) {
    val rnd0 = rnd.nextInt(c.n)
    val rnd1 = rnd.nextInt(c.n)
    val rnd2 = rnd.nextInt(1)

    poke(c.io.A, rnd0)
    poke(c.io.B, rnd1)
    poke(c.io.Cin, rnd2)
    step(1)
    val rsum = rnd0 + rnd1 + rnd2
    expect(c.io.Sum, rsum & ~(BigInt(-1) << c.n))
    expect(c.io.Cout, rsum & (BigInt(1) << c.n))
  }
}

class AdderTester extends ChiselFlatSpec {
  "Adder" should "correctly add randomly generated numbers" in {
    runPeekPokeTester(() => new Adder(16)){
      (c,b) => new AdderTests(c,b)}
  }
}
