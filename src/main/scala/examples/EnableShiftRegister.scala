// See LICENSE.txt for license details.
package examples

import chisel3._

class EnableShiftRegister extends Module {
  val io = IO(new Bundle {
    val in    = Input(UInt(width=4))
    val shift = Input(Bool())
    val out   = Output(UInt(width=4))
  })
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
