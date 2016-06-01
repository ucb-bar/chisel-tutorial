package problems

import Chisel._
import Chisel.iotesters._

class Accumulator extends Module {
  val io = new Bundle {
    val in  = UInt(width = 1, dir = INPUT)
    val out = UInt(width = 8, dir = OUTPUT)
  }
  // COUNT INCOMING TRUES
  // FILL IN HERE ...
  io.out := UInt(0)
}

class AccumulatorTests(c: Accumulator, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  var tot = 0
  for (t <- 0 until 16) {
    val in = rnd.nextInt(2)
    poke(c.io.in, in)
    step(1)
    if (in == 1) tot += 1
    expect(c.io.out, tot)
  }
}
