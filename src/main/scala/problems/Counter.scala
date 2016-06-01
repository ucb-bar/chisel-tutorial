package problems

import Chisel._
import Counter._

object Counter {

  def wrapAround(n: UInt, max: UInt) = 
    Mux(n > max, UInt(0), n)

  // ---------------------------------------- \\
  // Modify this function to increment by the
  // amt only when en is asserted
  // ---------------------------------------- \\

  def counter(max: UInt, en: Bool, amt: UInt): UInt = {
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
