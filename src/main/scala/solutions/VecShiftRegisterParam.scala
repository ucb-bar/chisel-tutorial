// See LICENSE.txt for license details.
package solutions

import chisel3._

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width = w))
    val out = Output(UInt(width = w))
  })

  val initValues = Seq.fill(n) { UInt(value = 0, width = 8) }
  val delays = Reg(init = Vec(initValues))

  for (i <- n-1 to 1 by -1) {
    delays(i) := delays(i - 1)
  }

  delays(0) := io.in
  io.out := delays(n-1)
}
