// See LICENSE.txt for license details.
package problems

import chisel3._

class Accumulator extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width = 1))
    val out = Output(UInt(width = 8))
  })
  // COUNT INCOMING TRUES
  // FILL IN HERE ...
  io.out := 0.U
}
