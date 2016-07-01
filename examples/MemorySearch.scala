// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._

class MemorySearch extends Module {
  val io = new Bundle {
    val target  = UInt(INPUT,  4)
    val en      = Bool(INPUT)
    val done    = Bool(OUTPUT)
    val address = UInt(OUTPUT, 3)
  }
  val index = Reg(init = UInt(0, width = 3))
  val elts  = Vec(UInt(0), UInt(4), UInt(15), UInt(14),
                  UInt(2), UInt(5), UInt(13))
  val elt   = elts(index)
  val over  = !io.en && ((elt === io.target) || (index === UInt(7)))
  when (io.en) {
    index := UInt(0)
  } .elsewhen (!over) {
    index := index + UInt(1)
  }
  io.done    := over
  io.address := index
}

class MemorySearchTests(c: MemorySearch) extends Tester(c) {
  val list = c.elts.map(int(_)) 
  val n = 8
  val maxT = n * (list.length + 3)
  for (k <- 0 until n) {
    val target = rnd.nextInt(16)
    poke(c.io.en,     1)
    poke(c.io.target, target)
    step(1)
    poke(c.io.en,     0)
    do {
      step(1)
    } while (peek(c.io.done) == 0 && t < maxT)
    val addr = peek(c.io.address).toInt
    expect(addr == list.length || list(addr) == target, 
           "LOOKING FOR " + target + " FOUND " + addr)
  }
}
