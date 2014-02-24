package TutorialProblems

import Chisel._

class DynamicMemorySearch extends Module {
  val io = new Bundle {
    val isWr   = Bool(INPUT)
    val wrAddr = UInt(INPUT, 3)
    val data   = UInt(INPUT, 4)
    val en     = Bool(INPUT)
    val target = UInt(OUTPUT, 3)
    val done   = Bool(OUTPUT)
  }
  val index  = Reg(init = UInt(0, width = 3))
  val memVal = UInt(0) // TODO REPLACE HERE WITH MEM
  val done   = !io.en && ((memVal === io.data) || (index === UInt(7)))
  when (io.en) {
    index := UInt(0)
  } .elsewhen (done === Bool(false)) {
    index := index + UInt(1)
  }
  io.done   := done
  io.target := index
}

class DynamicMemorySearchTests(c: DynamicMemorySearch) extends Tester(c) {
  val list  = Array.fill(8){ 0 }
  for (k <- 0 until 16) {
    // WRITE A WORD
    poke(c.io.en,   0)
    poke(c.io.isWr, 1)
    val wrAddr   = rnd.nextInt(8-1)
    val data     = rnd.nextInt(16)
    poke(c.io.wrAddr, wrAddr)
    poke(c.io.data,   data)
    step()
    list(wrAddr) = data
    // SETUP SEARCH
    val target   = rnd.nextInt(16)
    poke(c.io.isWr, 0)
    poke(c.io.data, target)
    poke(c.io.en, 1)
    step()
    do {
      poke(c.io.en, 0)
      step()
    } while (peek(c.io.done) == 0)
    val addr = peek(c.io.target).toInt
    expect(addr == list.length || list(addr) == target, 
           "LOOKING FOR " + target + " FOUND " + addr)
  }
}
