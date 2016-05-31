package solutions

import Chisel._

class Accumulator extends Module {
  val io = new Bundle {
    val in  = UInt(width = 1, dir = INPUT)
    val out = UInt(width = 8, dir = OUTPUT)
  }
  val accumulator = Reg(init=UInt(0, 8))
  accumulator := accumulator + io.in
  io.out := accumulator
}
