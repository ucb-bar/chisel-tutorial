package problems

import Chisel._

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
  val memVal = 0.U
  val done   = !io.en && ((memVal === io.data) || (index === UInt(n-1)))
  // ...
  when (io.en) {
    index := 0.U
  } .elsewhen (done === Bool(false)) {
    index := index + 1.U
  }
  io.done   := done
  io.target := index
}
