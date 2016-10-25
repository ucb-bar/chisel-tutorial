// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util._

class VendingMachineSwitch extends Module {
  val io = IO(new Bundle {
    val nickel = Input(Bool())
    val dime   = Input(Bool())
    val valid  = Output(Bool())
  })
  val s_idle :: s_5 :: s_10 :: s_15 :: s_ok :: Nil = Enum(UInt(), 5)
  val state = Reg(init = s_idle)
  
  switch (state) {
    is (s_idle) {
      when (io.nickel) { state := s_5 }
      when (io.dime) { state := s_10 }
    }
    // Fill out the remaining states here
  }
  io.valid := (state === s_ok)
}
