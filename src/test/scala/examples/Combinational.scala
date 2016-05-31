package examples

import Chisel._
import Chisel.iotesters._

class CombinationalTests(c: Combinational, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val maxInt = 1 << 16
  for (i <- 0 until 10) {
    val x = rnd.nextInt(maxInt)
    val y = rnd.nextInt(maxInt)
    poke(c.io.x, x)
    poke(c.io.y, y)
    step(1)
    expect(c.io.z, (x + y)&(maxInt-1))
  }
}

class CombinationalTester extends ChiselFlatSpec {
  "Combinational" should "correctly add randomly generated numbers" in {
    runPeekPokeTester(() => new Combinational){
      (c,b) => new CombinationalTests(c,b)}
  }
}

