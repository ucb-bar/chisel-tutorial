package examples

import Chisel._


class EnableShiftRegister extends Module {
  val io = new Bundle {
    val in    = UInt(INPUT, 4)
    val shift = Bool(INPUT)
    val out   = UInt(OUTPUT, 4)
  }
  val r0 = Reg(UInt())
  val r1 = Reg(UInt())
  val r2 = Reg(UInt())
  val r3 = Reg(UInt())
  when(reset) {
    r0 := UInt(0, 4)
    r1 := UInt(0, 4)
    r2 := UInt(0, 4)
    r3 := UInt(0, 4)
  } .elsewhen(io.shift) {
    r0 := io.in
    r1 := r0
    r2 := r1
    r3 := r2
  }
  io.out := r3
}
