// See LICENSE.txt for license details.
package solutions

import Chisel._

class Adder(val w: Int) extends Module {
  val io = new Bundle {
    val in0 = UInt(INPUT,  w)
    val in1 = UInt(INPUT,  w)
    val out = UInt(OUTPUT, w)
  }
  val x = "h0".U
  io.out := io.in0 + io.in1
}
