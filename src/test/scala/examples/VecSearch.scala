package examples


import Chisel.iotesters._

class VecSearchTests(c: VecSearch, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val list = VecSearchTest.pattern
  for (elt <- list) {
    expect(c.io.out, elt)
    step(1)
  }
}

class VecSearchTester extends ChiselFlatSpec {
  "VecSearch" should "correctly look for element in vector" in {
    runPeekPokeTester(() => new VecSearch) {
      (c,b) => new VecSearchTests(c,b)
    }
  }
}

