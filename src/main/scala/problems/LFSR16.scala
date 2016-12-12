// See LICENSE.txt for license details.
package problems

import chisel3._

class LFSR16 extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val out = Output(UInt(16.W))
  })

  // COMPUTE LFSR16 HERE
  io.out := 0.U
}
