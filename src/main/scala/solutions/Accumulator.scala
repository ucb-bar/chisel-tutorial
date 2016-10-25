// See LICENSE.txt for license details.
package solutions

import chisel3._

class Accumulator extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width = 1))
    val out = Output(UInt(width = 8))
  })
  val accumulator = Reg(init=UInt(0, 8))
  accumulator := accumulator + io.in
  io.out := accumulator
}
