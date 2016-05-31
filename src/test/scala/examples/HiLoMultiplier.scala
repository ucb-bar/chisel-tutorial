package examples

import Chisel._
import Chisel.iotesters._

class HiLoMultiplierTests(c: HiLoMultiplier, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  for (t <- 0 until 4) {
    val rnd0 = rnd.nextInt(65535)
    val rnd1 = rnd.nextInt(65535)
    val ref_out = rnd0 * rnd1
    poke(c.io.A, rnd0)
    poke(c.io.B, rnd1)
    step(1)
    expect(c.io.Lo, ref_out & BigInt("ffff", 16))
    expect(c.io.Hi, (ref_out & BigInt("ffff0000", 16)) >> 16)
  }
}
