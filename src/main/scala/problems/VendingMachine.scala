package problems

import Chisel._

class VendingMachine extends Module {
  val io = new Bundle {
    val nickel = Bool(INPUT)
    val dime   = Bool(INPUT)
    val valid  = Bool(OUTPUT) }
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil = 
    Enum(UInt(), 5)
  val state = Reg(init=sIdle)

  // flush it out ...
  state := s5
  io.valid := (state === sOk)
}
