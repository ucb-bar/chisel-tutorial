package examples

import Chisel._


class ResetShiftRegister extends Module {
  val io = new Bundle {
    val in    = UInt(INPUT, 4)
    val shift = Bool(INPUT)
    val out   = UInt(OUTPUT, 4)
  }
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
