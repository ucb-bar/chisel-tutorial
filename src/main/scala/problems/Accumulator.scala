// See LICENSE.txt for license details.
package problems

import chisel3._

class Accumulator extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(1.W))
    val out = Output(UInt(8.W))
  })
  // COUNT INCOMING TRUES
  // FILL IN HERE ...
  io.out := 0.U
}
