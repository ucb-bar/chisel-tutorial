// See LICENSE.txt for license details.
package problems

import chisel3._

class Adder(val w: Int) extends Module {
  val io = new Bundle {
    val in0 = UInt(INPUT,  1)
    val in1 = UInt(INPUT,  1)
    val out = UInt(OUTPUT, 1)
  }
  // fill in here
  io.out := 0.U
}
