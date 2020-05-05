// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class CounterTest(c: Counter) extends PeekPokeTester(c) {
  val maxInt  = 16
  var curCnt  = 0

  def intWrapAround(n: Int, max: Int) = 
    if(n > max) 0 else n

  poke(c.io.inc, 0)
  poke(c.io.amt, 0)

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
  behavior of "Counter"
  backends foreach {backend =>
    it should s"correctly count randomly generated numbers in $backend" in {
      Driver(() => new Counter, backend)(c => new CounterTest(c)) should be (true)
    }
  }
}
