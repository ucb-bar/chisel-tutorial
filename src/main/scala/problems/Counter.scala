// See LICENSE.txt for license details.
package problems

import chisel3._
// import Counter._

object Counter {

  def wrapAround(n: UInt, max: UInt) = 
    Mux(n > max, 0.U, n)

  // ---------------------------------------- \\
  // Modify this function to increment by the
  // amt only when en is asserted
  // ---------------------------------------- \\

  def counter(max: UInt, en: Bool, amt: UInt): UInt = {
    val x = Reg(init=UInt(0, max.getWidth))
    x := wrapAround(x + 1.U, max)
    x
  }

  // ---------------------------------------- \\

}

class Counter extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val amt = Input(UInt(width = 4))
    val tot = Output(UInt(width = 8))
  })

  io.tot := Counter.counter(255.U, io.inc, io.amt)

}
