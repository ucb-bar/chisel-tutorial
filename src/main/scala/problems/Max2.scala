// See LICENSE.txt for license details.
package problems

import chisel3._

class Max2 extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(width = 8))
    val in1 = Input(UInt(width = 8))
    val out = Output(UInt(width = 8))
  })
  io.out := Mux(io.in0 > io.in1, io.in0, io.in1)
}
