// See LICENSE.txt for license details.
package problems

import chisel3._

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })
  /// fill in here ...
  io.out := 0.U
}
