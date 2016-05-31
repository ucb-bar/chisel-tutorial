package examples


import Chisel.iotesters._

class FullAdderTests(c: FullAdder, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  for (t <- 0 until 4) {
    val a    = rnd.nextInt(2)
    val b    = rnd.nextInt(2)
    val cin  = rnd.nextInt(2)
    val res  = a + b + cin
    val sum  = res & 1
    val cout = (res >> 1) & 1
    poke(c.io.a, a)
    poke(c.io.b, b)
    poke(c.io.cin, cin)
    step(1)
    expect(c.io.sum, sum)
    expect(c.io.cout, cout)
  }
}

class FullAdderTester extends ChiselFlatSpec {
  "FullAdder" should "correctly add randomly generated numbers and show carry" in {
    runPeekPokeTester(() => new FullAdder) {
      (c,b) => new FullAdderTests(c,b)
    }
  }
}
