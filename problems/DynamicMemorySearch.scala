package TutorialProblems

import Chisel._
import Chisel.hwiotesters._

class DynamicMemorySearch(val n: Int, val w: Int) extends Module {
  val io = new Bundle {
    val isWr   = Bool(INPUT)
    val wrAddr = UInt(INPUT,  log2Up(n))
    val data   = UInt(INPUT,  w)
    val en     = Bool(INPUT)
    val target = UInt(OUTPUT, log2Up(n))
    val done   = Bool(OUTPUT)
  }
  val index  = Reg(init = UInt(0, width = log2Up(n)))
  val memVal = UInt(0)
  val done   = !io.en && ((memVal === io.data) || (index === UInt(n-1)))
  // ...
  when (io.en) {
    index := UInt(0)
  } .elsewhen (done === Bool(false)) {
    index := index + UInt(1)
  }
  io.done   := done
  io.target := index
}

class DynamicMemorySearchTests(c: DynamicMemorySearch) extends ClassicTester(c) {
  val list = Array.fill(c.n){ 0 }
  // Initialize the memory.
  for (k <- 0 until c.n) {
    poke(c.io.en, 0)
    poke(c.io.isWr, 1)
    poke(c.io.wrAddr, k)
    poke(c.io.data, 0)
    step(1)
  }

  for (k <- 0 until 16) {
    // WRITE A WORD
    poke(c.io.en,   0)
    poke(c.io.isWr, 1)
    val wrAddr = rnd.nextInt(c.n-1)
    val data   = rnd.nextInt((1 << c.w) - 1) + 1 // can't be 0
    poke(c.io.wrAddr, wrAddr)
    poke(c.io.data,   data)
    step(1)
    list(wrAddr) = data
    // SETUP SEARCH
    val target = if (k > 12) rnd.nextInt(1 << c.w) else data
    poke(c.io.isWr, 0)
    poke(c.io.data, target)
    poke(c.io.en,   1)
    step(1)
    do {
      poke(c.io.en, 0)
      step(1)
    } while (peek(c.io.done) == 0)
    val addr = peek(c.io.target).toInt
    if (list contains target)
      assert(list(addr) == target, "LOOKING FOR " + target + " FOUND " + addr)
    else
      assert(addr==(list.length-1), "LOOKING FOR " + target + " FOUND " + addr)
  }
}
