// See LICENSE.txt for license details.
package solutions

import chisel3._

class VecShiftRegisterSimple extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width = 8))
    val out = Output(UInt(width = 8))
  })

  val initValues = Seq.fill(4) { UInt(value = 0, width = 8) }
  val delays = Reg(init = Vec(initValues))

  delays(0) := io.in
  delays(1) := delays(0)
  delays(2) := delays(1)
  delays(3) := delays(2)
  io.out    := delays(3)
}
