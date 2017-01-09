// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.Enum

// Problem:
//
// Implement vending machine using 'when' states
// 'nickel' is 5 worth coin
// 'dime'   is 10 worth coin
// 'sOk' is reached when there are 20 or more worth coins in machine
//
class VendingMachine extends Module {
  val io = IO(new Bundle {
    val nickel = Input(Bool())
    val dime   = Input(Bool())
    val valid  = Output(Bool())
  })
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil =
    Enum(5)
  val state = Reg(init=sIdle)

  // Implement below ----------

  state := s5

  // Implement above ----------

  io.valid := (state === sOk)
}
