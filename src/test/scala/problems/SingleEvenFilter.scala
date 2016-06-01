package problems

import Chisel._
import Chisel.iotesters._

class SingleEvenFilterTests[T <: UInt](c: SingleEvenFilter[T], b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val maxInt  = 1 << 16
  for (i <- 0 until 10) {
    val in = rnd.nextInt(maxInt)
    poke(c.io.in.valid, 1)
    poke(c.io.in.bits, in)
    val isSingleEven = (in <= 9) && (in%2 == 1)
    step(1)
    expect(c.io.out.valid, if (isSingleEven) 1 else 0)
    expect(c.io.out.bits, in)
  }
}
