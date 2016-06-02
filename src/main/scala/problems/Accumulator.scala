package problems

import Chisel._

class Accumulator extends Module {
  val io = new Bundle {
    val in  = UInt(width = 1, dir = INPUT)
    val out = UInt(width = 8, dir = OUTPUT)
  }
  // COUNT INCOMING TRUES
  // FILL IN HERE ...
  io.out := 0.U
}
