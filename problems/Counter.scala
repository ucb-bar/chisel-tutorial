package TutorialProblems

import Chisel._
import Node._
import Counter._

object Counter {

  def wrapAround(n: UInt, max: UInt) = 
    Mux(n > max, UInt(0), n)

  // ---------------------------------------- \\
  // Modify this function to increment by the
  // amt only when en is asserted
  // ---------------------------------------- \\

  def counter(max: UInt, en: Bool, amt: UInt) = {
    val x = Reg(init=UInt(0, max.getWidth))
    x := wrapAround(x + UInt(1), max)
    x
  }

  // ---------------------------------------- \\

}

class Counter extends Module {
  val io = new Bundle {
    val inc = Bool(INPUT)
    val amt = UInt(INPUT,  4)
    val tot = UInt(OUTPUT, 8)
  }

  io.tot := Counter.counter(UInt(255), io.inc, io.amt)

}

class CounterTest(c: Counter) extends Tester(c) {
  val maxInt  = 16
  var curCnt  = 0

  def intWrapAround(n: Int, max: Int) = 
    if(n > max) 0 else n

  // let it spin for a bit
  for (i <- 0 until 5) {
    step(vars, isTrace = false)
  }

  for (i <- 0 until 10) {
    val inc = rnd.nextBoolean()
    val amt = rnd.nextInt(maxInt)
    poke(c.io.inc, if (inc) 1 else 0)
    poke(c.io.amt, amt)
    step(1)
    expect(c.io.tot, curCnt)
    curCnt = if(inc) intWrapAround(curCnt + amt, 255) else curCnt
  }
}

