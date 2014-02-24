package TutorialProblems

import Chisel._
import Node._

class Accumulator extends Module {
  val io = new Bundle {
    val in  = UInt(width = 1, dir = INPUT)
    val out = UInt(width = 8, dir = OUTPUT)
  }
  // COUNT INCOMING TRUES
  // FILL IN HERE ...
  io.out := UInt(0)
}

class AccumulatorTests(c: Accumulator) extends Tester(c) {
  var tot = 0
  for (t <- 0 until 16) {
    val in = rnd.nextInt(2)
    poke(c.io.in, in)
    step()
    expect(c.io.out, tot)
    if (in == 1) tot += 1
  }
}
