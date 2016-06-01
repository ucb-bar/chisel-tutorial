package problems

import Chisel._

class VecShiftRegister extends Module {
  val io = new Bundle {
    val ins   = Vec(4, UInt(INPUT, 4))
    val load  = Bool(INPUT)
    val shift = Bool(INPUT)
    val out   = UInt(OUTPUT, 4)
  }
  // FILL IN LOADABLE SHIFT REGISTER USING VEC 
  io.out := UInt(0)
}
