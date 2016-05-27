package TutorialExamples

import Chisel._
import Chisel.iotesters._

//A 4-bit adder with carry in and carry out
class Adder4 extends Module {
  val io = new Bundle {
    val A    = UInt(INPUT, 4)
    val B    = UInt(INPUT, 4)
    val Cin  = UInt(INPUT, 1)
    val Sum  = UInt(OUTPUT, 4)
    val Cout = UInt(OUTPUT, 1)
  }
  //Adder for bit 0
  val Adder0 = Module(new FullAdder())
  Adder0.io.a := io.A(0)
  Adder0.io.b := io.B(0)
  Adder0.io.cin := io.Cin
  val s0 = Adder0.io.sum
  //Adder for bit 1
  val Adder1 = Module(new FullAdder())
  Adder1.io.a := io.A(1)
  Adder1.io.b := io.B(1)
  Adder1.io.cin := Adder0.io.cout
  val s1 = Cat(Adder1.io.sum, s0)
  //Adder for bit 2
  val Adder2 = Module(new FullAdder())
  Adder2.io.a := io.A(2)
  Adder2.io.b := io.B(2)
  Adder2.io.cin := Adder1.io.cout
  val s2 = Cat(Adder2.io.sum, s1)
  //Adder for bit 3
  val Adder3 = Module(new FullAdder())
  Adder3.io.a := io.A(3)
  Adder3.io.b := io.B(3)
  Adder3.io.cin := Adder2.io.cout
  io.Sum := Cat(Adder3.io.sum, s2).toUInt()
  io.Cout := Adder3.io.cout
}

class Adder4Tests(c: Adder4, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val rnd2 = rnd.nextInt(2)
  for (t <- 0 until 4) {
    val rnd0 = rnd.nextInt(16)
    val rnd1 = rnd.nextInt(16)
    val rnd2 = rnd.nextInt(2)
    poke(c.io.A,   rnd0)
    poke(c.io.B,   rnd1)
    poke(c.io.Cin, rnd2)
    step(1)
    val rsum = (rnd0 & 0xF) + (rnd1 & 0xF) + (rnd2 & 0x1)
    expect(c.io.Sum, (rsum & 0xF))
    expect(c.io.Cout, rsum >> 4)
  }
}
