// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.Enum

class VendingMachine extends Module {
  val io = IO(new Bundle {
    val nickel = Input(Bool())
    val dime   = Input(Bool())
    val valid  = Output(Bool())
  })
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil = 
    Enum(UInt(), 5)
  val state = Reg(init=sIdle)

  // flush it out ...
  state := s5
  io.valid := (state === sOk)
}
