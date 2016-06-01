package problems

import Chisel._

class VecShiftRegisterSimple extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  8)
    val out = UInt(OUTPUT, 8)
  }
  val delays = Reg(init = Vec.fill(4)(UInt(0, width = 8)))
  /// fill in here ...
  io.out := UInt(0)
}
