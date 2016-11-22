// See LICENSE.txt for license details.
package solutions

import chisel3._

class Adder(val w: Int) extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(w.W))
    val in1 = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })
  io.out := io.in0 + io.in1
}
