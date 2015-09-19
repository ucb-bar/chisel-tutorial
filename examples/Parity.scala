package TutorialExamples

import Chisel._

class Parity extends Module {
  val io = new Bundle {
    val in  = Bool(INPUT)
    val out = Bool(OUTPUT) }
  val s_even :: s_odd :: Nil = Enum(UInt(), 2)
  val state  = Reg(init=s_even)
  when (io.in) {
    when (state === s_even) { state := s_odd  }
    .otherwise              { state := s_even }
  }
  io.out := (state === s_odd)
}

trait ParityTests extends Tests {
  def tests(c: Parity) {
    var isOdd = 0
    for (t <- 0 until 10) {
      val bit = rnd.nextInt(2)
      poke(c.io.in, bit)
      step(1)
      isOdd = (isOdd + bit) % 2;
      expect(c.io.out, isOdd)
    }
  }
}

class ParityTester(c: Parity) extends Tester(c) with ParityTests {
  tests(c)
}

