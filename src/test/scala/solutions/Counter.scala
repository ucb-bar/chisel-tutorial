// See LICENSE.txt for license details.
package solutions

import Chisel.iotesters.{ Backend => TesterBackend, _ }

class CounterTest(c: Counter, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  val maxInt  = 16
  var curCnt  = 0

  def intWrapAround(n: Int, max: Int) = 
    if(n > max) 0 else n

  // let it spin for a bit
  for (i <- 0 until 5) {
    step(1)
  }

  for (i <- 0 until 10) {
    val inc = rnd.nextBoolean()
    val amt = rnd.nextInt(maxInt)
    poke(c.io.inc, if (inc) 1 else 0)
    poke(c.io.amt, amt)
    step(1)
    curCnt = if(inc) intWrapAround(curCnt + amt, 255) else curCnt
    expect(c.io.tot, curCnt)
  }
}

class CounterTester extends ChiselFlatSpec {
  "Counter" should "correctly count randomly generated numbers" in {
    runPeekPokeTester(() => new Counter){
      (c,b) => new CounterTest(c,b)}
  }
}
