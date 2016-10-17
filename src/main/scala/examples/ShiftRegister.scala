// See LICENSE.txt for license details.
package examples

import chisel3._

class ShiftRegister extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width= 1))
    val out = Output(UInt(width=1))
  })
  val r0 = Reg(next = io.in)
  val r1 = Reg(next = r0)
  val r2 = Reg(next = r1)
  val r3 = Reg(next = r2)
  io.out := r3
}
