package examples

import Chisel._
import Chisel.iotesters._

class Darken extends Module {
  val io = new Bundle {
    val in  = Bits(INPUT, 8)
    val out = Bits(OUTPUT, 8)
  }

  io.out := io.in << UInt(1)
}
