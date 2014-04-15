package TutorialExamples

import Chisel._
import scala.collection.mutable.HashMap
import scala.math._

class ClockedAccumulator(c: Clock) extends Module(clock = c) {
  val io = new Bundle {
    val inc = Decoupled(UInt(width = 32)).flip()
    val sum = Decoupled(UInt(width = 32))
  }
  val accumulator = Reg(init = UInt(0, 32))
  when (io.inc.valid && io.sum.ready) {
    accumulator := accumulator + io.inc.bits
  }
  io.inc.ready := io.sum.ready
  io.sum.valid := io.inc.valid
  io.sum.bits  := accumulator
}

class MultiClockDomain extends Module {
  val io = new Bundle {
    val start = Bool(INPUT)
    val sum   = Decoupled(UInt(OUTPUT))
  }
  val fastClock = new Clock()
  val slowClock = new Clock()

  val a0 = Module(new ClockedAccumulator(fastClock))
  a0.io.inc.valid := io.start
  a0.io.inc.bits  := UInt(1)

  val asyncFifo = Module(new AsyncFifo(UInt(width=32), 32, fastClock, slowClock))
  asyncFifo.io.enq <> a0.io.sum

  val a1 = Module(new ClockedAccumulator(slowClock))
  a1.io.inc <> asyncFifo.io.deq
  io.sum.bits     := a1.io.sum.bits
  io.sum.valid    := a1.io.sum.valid
  a1.io.sum.ready := io.sum.ready
}

class MultiClockDomainTests(c: MultiClockDomain) extends Tester(c) {
  // setting up clocks
  val clocks = new HashMap[Clock, Int]
  clocks(Driver.implicitClock) = 2
  clocks(c.fastClock) = 4
  clocks(c.slowClock) = 6
  setClocks(clocks)

  // out of reset, but not starting accumulators yet
  for (i <- 0 until 5) {
    poke(c.io.start,     0)
    poke(c.io.sum.ready, 0)
    step(1)
  }

  val answers = Array(0, 0, 1, 3, 6, 10, 15, 21, 28, 36)
  while (t < 10) {
    poke(c.io.start,     1)
    poke(c.io.sum.ready, 1)
    step(1)
    println("DELTA " + delta)
    // only check outputs on valid && 6 deltas have passed
    if (peek(c.io.sum.valid) == 1 && (delta % 6 == 0)) {
      expect(c.io.sum.bits, answers(t))
    }
  }
}

