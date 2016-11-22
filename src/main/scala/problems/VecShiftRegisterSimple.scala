// See LICENSE.txt for license details.
package problems

import chisel3._

class VecShiftRegisterSimple extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(8.W))
    val out = Output(UInt(8.W))
  })

  val initValues = Seq.fill(4) { 0.U(8.W) }
  val delays = Reg(init = Vec(initValues))

  /// fill in here ...
  io.out := 0.U
}
