package examples


import Chisel.iotesters._

class ShiftRegisterTests(c: ShiftRegister, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val reg     = Array.fill(4){ 0 }
  for (t <- 0 until 64) {
    val in = rnd.nextInt(2)
    poke(c.io.in, in)
    step(1)
    for (i <- 3 to 1 by -1)
      reg(i) = reg(i-1)
    reg(0) = in
    if (t >= 4) expect(c.io.out, reg(3))
  }
}

class ShiftRegisterTester extends ChiselFlatSpec {
  "ShiftRegister" should "shift a number through a series of registers" in {
    runPeekPokeTester(() => new ShiftRegister) {
      (c,b) => new ShiftRegisterTests(c,b)
    }
  }
}

