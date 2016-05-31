package examples

import Chisel._
import Chisel.iotesters._

class ShiftRegister extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  1)
    val out = UInt(OUTPUT, 1)
  }
  val r0 = Reg(next = io.in)
  val r1 = Reg(next = r0)
  val r2 = Reg(next = r1)
  val r3 = Reg(next = r2)
  io.out := r3
}
