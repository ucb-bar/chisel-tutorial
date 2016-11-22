// See LICENSE.txt for license details.
package problems

import chisel3._

class Adder(val w: Int) extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(1.W))
    val in1 = Input(UInt(1.W))
    val out = Output(UInt(1.W))
  })
  // fill in here
  io.out := 0.U
}
