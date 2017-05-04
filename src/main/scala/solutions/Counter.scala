// See LICENSE.txt for license details.
package solutions

import chisel3._
// import Counter._

// Problem:
//
// Counter should be incremented by the 'amt'
// every clock if 'en' is asserted
//
object Counter {

  def wrapAround(n: UInt, max: UInt) = 
    Mux(n > max, 0.U, n)

  def counter(max: UInt, en: Bool, amt: UInt): UInt = {
    val x = RegInit(0.U(max.getWidth.W))
    when (en) { x := wrapAround(x + amt, max) }
    x
  }

}

class Counter extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val amt = Input(UInt(4.W))
    val tot = Output(UInt(8.W))
  })

  io.tot := Counter.counter(255.U, io.inc, io.amt)

}
