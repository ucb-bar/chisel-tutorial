package problems

import Chisel._

class Memo extends Module {
  val io = new Bundle {
    val wen     = Bool(INPUT)
    val wrAddr  = UInt(INPUT,  8)
    val wrData  = UInt(INPUT,  8)
    val ren     = Bool(INPUT)
    val rdAddr  = UInt(INPUT,  8)
    val rdData  = UInt(OUTPUT, 8)
  }
  val mem = Mem(UInt(width = 8), 256)

  // --------------------------------------------------- \\
  // When wen is asserted, write wrData to mem at wrAddr 
  // When ren is asserted, rdData holds the output out of
  // reading the mem at rdAddr
  // --------------------------------------------------- \\


  // --------------------------------------------------- \\

}
