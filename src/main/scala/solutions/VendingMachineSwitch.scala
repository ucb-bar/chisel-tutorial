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
//
class VendingMachineSwitch extends Module {
  val io = IO(new Bundle {
    val nickel = Input(Bool())
    val dime   = Input(Bool())
    val valid  = Output(Bool())
  })
  val s_idle :: s_5 :: s_10 :: s_15 :: s_ok :: Nil = Enum(5)
  val state = Reg(init = s_idle)

  switch (state) {
    is (s_idle) {
      when (io.nickel) { state := s_5 }
      when (io.dime) { state := s_10 }
    }
    is (s_5) {
      when (io.nickel) { state := s_10 }
      when (io.dime) { state := s_15 }
    }
    is (s_10) {
      when (io.nickel) { state := s_15 }
      when (io.dime) { state := s_ok }
    }
    is (s_15) {
      when (io.nickel) { state := s_ok }
      when (io.dime) { state := s_ok }
    }
    is (s_ok) {
      state := s_idle
    }
  }
  io.valid := (state === s_ok)
}
