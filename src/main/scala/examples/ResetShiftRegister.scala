// See LICENSE.txt for license details.
package examples

import chisel3._

class ResetShiftRegister extends Module {
  val io = IO(new Bundle {
    val in    = Input(UInt(width=4))
    val shift = Input(Bool())
    val out   = Output(UInt(width=4))
  })
  // Register reset to zero
  val r0 = Reg(init = UInt(0, width = 4))
  val r1 = Reg(init = UInt(0, width = 4))
  val r2 = Reg(init = UInt(0, width = 4))
  val r3 = Reg(init = UInt(0, width = 4))
  when (io.shift) {
    r0 := io.in
    r1 := r0
    r2 := r1
    r3 := r2
  }
  io.out := r3
}
