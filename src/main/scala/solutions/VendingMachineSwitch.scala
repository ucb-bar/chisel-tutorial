// See LICENSE.txt for license details.
package solutions

import chisel3._
import chisel3.util._

// Problem:
//
// Implement a vending machine using a 'switch' statement.
// 'nickel' is a 5 cent coin
// 'dime'   is 10 cent coin
// 'sOk' is reached when there are coins totalling 20 cents or more in the machine.
// The vending machine should return to the 'sIdle' state from the 'sOk' state.
//
class VendingMachineSwitch extends Module {
  val io = IO(new Bundle {
    val nickel = Input(Bool())
    val dime   = Input(Bool())
    val valid  = Output(Bool())
  })
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil = Enum(5)
  val state = RegInit(sIdle)

  switch (state) {
    is (sIdle) {
      when (io.nickel) { state := s5 }
      when (io.dime) { state := s10 }
    }
    is (s5) {
      when (io.nickel) { state := s10 }
      when (io.dime) { state := s15 }
    }
    is (s10) {
      when (io.nickel) { state := s15 }
      when (io.dime) { state := sOk }
    }
    is (s15) {
      when (io.nickel) { state := sOk }
      when (io.dime) { state := sOk }
    }
    is (sOk) {
      state := sIdle
    }
  }
  io.valid := (state === sOk)
}
