package problems

import Chisel._

class LFSR16 extends Module {
  val io = new Bundle {
    val inc = Bool(INPUT)
    val out = UInt(OUTPUT, 16)
  }
  // COMPUTE LFSR16 HERE
  io.out := UInt(0)
}
