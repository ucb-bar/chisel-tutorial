// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._

class Functionality extends Module {
  val io = new Bundle {
    val x   = Bits(INPUT,  16)
    val y   = Bits(INPUT,  16)
    val z   = Bits(OUTPUT, 16)
  }
  def clb(a: UInt, b: UInt, c: UInt, d: UInt) =
    (a & b) | (~c & d)
  io.z := clb(io.x, io.y, io.x, io.y)
}

class FunctionalityTests(c: Functionality) extends Tester(c) {
  val maxInt = 1 << 16
  for (i <- 0 until 10) {
    val x = rnd.nextInt(maxInt)
    val y = rnd.nextInt(maxInt)
    poke(c.io.x, x)
    poke(c.io.y, y)
    step(1)
    expect(c.io.z, (x & y) | (~x & y))
  }
}
