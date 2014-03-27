package TutorialExamples

import Chisel._

class GCD extends Module {
  val io = new Bundle {
    val a  = UInt(INPUT,  16)
    val b  = UInt(INPUT,  16)
    val e  = Bool(INPUT)
    val z  = UInt(OUTPUT, 16)
    val v  = Bool(OUTPUT)
  }
  val x  = Reg(UInt())
  val y  = Reg(UInt())
  when   (x > y) { x := x - y }
  unless (x > y) { y := y - x }
  when (io.e) { x := io.a; y := io.b }
  io.z := x
  io.v := y === UInt(0)
  counter(io.a, io.b, io.e, io.z, io.v, x, y)
}

class GCDTests(c: GCD) extends CounterTester(c) {
  val (a, b, z) = (64, 48, 16)
  do {
    val first = if (t == 0) 1 else 0;
    poke(c.io.a, a)
    poke(c.io.b, b)
    poke(c.io.e, first)
    step(1)
  } while (t <= 1 || peek(c.io.v) == 0)
  expect(c.io.z, z)
}

class GCDWrapper extends CounterWrapper(new CounterConfiguration) {
  val top = Module(new GCD)
  
  // write 0 -> { GCD.io.a, GCD.io.b }
  val in_reg_0 = Reg(UInt())
  when (wen(0)) {
    in_reg_0 := io.in.bits
  }
  top.io.a := in_reg_0(31, 16)
  top.io.b := in_reg_0(15, 0)
  wready(0) := Bool(true)

  // write 1 -> GCD.io.e
  val in_reg_1 = Reg(UInt())
  when (wen(1)) {
    in_reg_1 := io.in.bits
  }
  top.io.e := in_reg_1(0)
  wready(0) := Bool(true)

  // read 0 -> GCD.io.z
  rdata(0)  := top.io.z
  rvalid(0) := top.io.v

  // read 1 -> GCD.io.v
  rdata(1)  := top.io.v
  rvalid(1) := Bool(true)
}

class GCDWrapperTests(c: GCDWrapper) extends CounterWrapperTester(c) {
  val (a, b, z) = (64, 48, 16)
  pokeAddr(0, a << 16 | b)
  do {
    val first = if (t == 0) 1 else 0;
    pokeAddr(1, first)
    step(1)
  } while (t <= 1 || peekAddr(1) == 0)
  expectAddr(0, z)
}
