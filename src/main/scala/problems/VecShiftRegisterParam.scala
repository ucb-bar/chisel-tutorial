// See LICENSE.txt for license details.
package problems

import chisel3._

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width = w))
    val out = Output(UInt(width = w))
  })
  /// fill in here ...
  io.out := 0.U
}
