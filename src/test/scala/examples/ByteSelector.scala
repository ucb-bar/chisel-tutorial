package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }

class ByteSelectorTests(c: ByteSelector, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  val test_in = 12345678
  for (t <- 0 until 4) {
    poke(c.io.in,     test_in)
    poke(c.io.offset, t)
    step(1)
    expect(c.io.out, (test_in >> (t * 8)) & 0xFF)
  }
}

class ByteSelectorTester extends ChiselFlatSpec {
  "ByteSelector" should "correctly select correct bits from an input" in {
    runPeekPokeTester(() => new ByteSelector){
      (c,b) => new ByteSelectorTests(c,b)}
  }
}
