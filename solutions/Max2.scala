package TutorialSolutions

import Chisel._
import Chisel.iotesters._

class Max2 extends Module {
  val io = new Bundle {
    val in0 = UInt(INPUT,  8)
    val in1 = UInt(INPUT,  8)
    val out = UInt(OUTPUT, 8)
  }
  io.out := Mux(io.in0 > io.in1, io.in0, io.in1)
}

class Max2Tests(c: Max2, b: Option[Backend] = None) extends ClassicTester(c, _backend=b) {
  for (i <- 0 until 10) {
    // FILL THIS IN HERE
    val in0 = rnd.nextInt(256)
    val in1 = rnd.nextInt(256)
    poke(c.io.in0, in0)
    poke(c.io.in1, in1)
    // FILL THIS IN HERE
    step(1)
    expect(c.io.out, if (in0 > in1) in0 else in1)
  }
}
