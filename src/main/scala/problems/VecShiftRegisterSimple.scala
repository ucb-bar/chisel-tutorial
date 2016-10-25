// See LICENSE.txt for license details.
package problems

import chisel3._

class VecShiftRegisterSimple extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width = 8))
    val out = Output(UInt(width = 8))
  })

  val initValues = Seq.fill(4) { UInt(value = 0, width = 8) }
  val delays = Reg(init = Vec(initValues))

  /// fill in here ...
  io.out := 0.U
}
