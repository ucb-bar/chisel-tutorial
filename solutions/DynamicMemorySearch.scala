package TutorialSolutions

import Chisel._

class DynamicMemorySearch extends Module {
  val io = new Bundle {
    val isWr   = Bool(INPUT)
    val wrAddr = UInt(INPUT, 3)
    val data   = UInt(INPUT, 4)
    val en     = Bool(INPUT)
    val elt    = UInt(OUTPUT, 4)
    val target = UInt(OUTPUT, 4)
    val done   = Bool(OUTPUT)
  }
  val index = Reg(init = UInt(0, width = 3))
  val list  = Mem(UInt(width = 4), 8)
  val elt   = list(index)
  io.elt   := elt
  val done  = !io.en && ((elt === io.data) || (index === UInt(7)))
  when (io.isWr) {
    list(io.wrAddr) := io.data
  } .elsewhen (io.en) {
    index := UInt(0)
  } .elsewhen (done === Bool(false)) {
    index := index + UInt(1)
  }
  io.done   := done
  io.target := index
}

class DynamicMemorySearchTests(c: DynamicMemorySearch) extends Tester(c) {
  val list = Array.fill(8){ 0 }
  for (k <- 0 until 16) {
    // WRITE A WORD
    poke(c.io.en,   0)
    poke(c.io.isWr, 1)
    val wrAddr = rnd.nextInt(8-1)
    val data   = rnd.nextInt(16)
    poke(c.io.wrAddr, wrAddr)
    poke(c.io.data,   data)
    step(1)
    list(wrAddr) = data
    // SETUP SEARCH
    // val target   = rnd.nextInt(16)
    val target   = data
    poke(c.io.isWr, 0)
    poke(c.io.data, target)
    poke(c.io.en,   1)
    step(1)
    do {
      poke(c.io.en, 0)
      step(1)
      peek(c.index); peek(c.io.elt)
    } while (peek(c.io.done) == 0)
    val addr = peek(c.io.target).toInt
    expect(addr == list.length || list(addr) == target, 
           "LOOKING FOR " + target + " FOUND " + addr)
  }
}
