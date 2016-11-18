// See LICENSE.txt for license details.
package problems

import chisel3._

class Adder(val w: Int) extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(width=1))
    val in1 = Input(UInt(width=1))
    val out = Output(UInt(width=1))
  })
  // fill in here
  io.out := 0.U
}
