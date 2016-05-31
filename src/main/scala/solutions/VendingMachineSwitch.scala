package solutions

import Chisel._

class VendingMachineSwitch extends Module {
  val io = new Bundle {
    val nickel = Bool(dir = INPUT)
    val dime   = Bool(dir = INPUT)
    val valid  = Bool(dir = OUTPUT)
  }
  val s_idle :: s_5 :: s_10 :: s_15 :: s_ok :: Nil = Enum(UInt(), 5)
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
  io.valid := (state ===s_ok)
}
