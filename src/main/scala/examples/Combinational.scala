package examples

import Chisel._


class Combinational extends Module {
  val io = new Bundle {
    val x   = UInt(INPUT,  16)
    val y   = UInt(INPUT,  16)
    val z   = UInt(OUTPUT, 16)
  }
  io.z := io.x + io.y
}
