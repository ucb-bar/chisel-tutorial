package TutorialSolutions

import Chisel._
import Node._
import scala.collection.mutable.HashMap

class RealGCDInput extends Bundle {
  val a = Bits(width = 16)
  val b = Bits(width = 16)
}

class RealGCD extends Module {
  val io  = new Bundle {
    val in  = Decoupled(new RealGCDInput()).flip()
    val out = new ValidIO(Bits(width = 16))
  }

  val x = Reg(UInt())
  val y = Reg(UInt())
  val first = Reg(init=Bool(true))

  io.in.ready := first || y === UInt(0)

  when (io.in.valid && io.in.ready) {
    x := io.in.bits.a
    y := io.in.bits.b
    first := Bool(false)
  }

  when (!first && y != UInt(0)) {
    when (x > y) { x := y; y := x}
    when (x <= y) { y := y - x }
  }

  io.out.bits := x
  io.out.valid := y === Bits(0) && !first
}

class RealGCDTests(c: RealGCD) extends Tester(c) {
  val inputs = List( (48, 32), (7, 3), (100, 10) )
  val outputs = List( 16, 1, 10)
  // let it spin for a bit
  for (i <- 0 until 5) {
    poke(c.io.in.valid, 0)
    step(1)
  }

  var i = 0
  var j = 0
  var t = 0

  var anyPassed = false

  while(t < 100 && (i < 3 || j < 3)) {
    t += 1
    if (i < 3) {
      poke(c.io.in.bits.a, inputs(i)._1)
      poke(c.io.in.bits.b, inputs(i)._2)
      poke(c.io.in.valid, 1)
    } else {
      poke(c.io.in.valid, 0)
    }

    // this advances the clock
    step(1)

    // bump counters and check outputs after advancing clock
    if (peek(c.io.in.ready) == 1) i += 1
    if (peek(c.io.out.valid) == 1) {
      expect(c.io.out.bits, outputs(j))
      j += 1
    }
  }
}
