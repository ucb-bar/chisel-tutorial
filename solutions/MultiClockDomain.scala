package TutorialSolutions

import Chisel._
import scala.math._
import scala.collection.mutable.HashMap

class Accumulator(c: Clock) extends Module(clock = c) {
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
  io.sum.bits := accumulator
}

class Top extends Module {
  val io = new Bundle {
    val start = Bool(INPUT)
    val sum = Decoupled(UInt(OUTPUT))
  }
  val fastClock = new Clock()
  val slowClock = Module.implicitClock*3

  val a0 = Module(new Accumulator(fastClock))
  a0.io.inc.valid := io.start
  a0.io.inc.bits := UInt(1)

  val asyncFifo = Module(new AsyncFifo(UInt(width=32), 32, fastClock, slowClock))
  asyncFifo.io.enq <> a0.io.sum

  val a1 = Module(new Accumulator(slowClock))
  a1.io.inc <> asyncFifo.io.deq
  io.sum.bits := a1.io.sum.bits
  io.sum.valid := a1.io.sum.valid
  a1.io.sum.ready := Bool(true)
}

class TopTest(c: Top) extends Tester(c, Array(c.io)) {
  defTests {
    // setting up clocks
    val clocks = new HashMap[Clock, Int]
    clocks(Module.implicitClock) = 2
    clocks(c.fastClock) = 4
    setClocks(clocks)

    // out of reset, but not starting accumulators yet
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    for (i <- 0 until 5) {
      svars(c.io.start) = Bool(false)
      svars(c.io.sum.ready) = Bool(false)
      step(svars, ovars, false)
    }

    var t = 0
    val answers = Array(0, 0, 1, 3, 6, 10, 15, 21, 28, 36)
    var anyPassed = false
    var allPassed = true
    while (t < 10) {
      svars(c.io.start) = Bool(true)
      svars(c.io.sum.ready) = Bool(true)
      step(svars, ovars)
      println(delta)
      // only check outputs on valid && 6 deltas have passed
      if (ovars(c.io.sum.valid).litValue() == 1 && (delta % 6 == 0)) {
        anyPassed = true
        allPassed = allPassed && answers(t) == ovars(c.io.sum.bits).litValue()
        t += 1
      }
    }


    anyPassed && allPassed
  }
}
