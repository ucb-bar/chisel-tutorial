package problems

import Chisel._

class Adder(val w: Int) extends Module {
  // fill in here
  val io = new Bundle {
    val in0 = UInt(INPUT,  1)
    val in1 = UInt(INPUT,  1)
    val out = UInt(OUTPUT, 1)
  }
  io.out := 0.U
}
