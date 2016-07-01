// See LICENSE.txt for license details.
package solutions

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
  val mem = Mem(256, UInt(width = 8))

  // --------------------------------------------------- \\
  // When wen is asserted, write wrData to mem at wrAddr 
  // When ren is asserted, rdData holds the output out of
  // reading the mem at rdAddr
  // --------------------------------------------------- \\

  // write
  when (io.wen) { mem(io.wrAddr) := io.wrData }
  
  // read
  io.rdData := 0.U
  when (io.ren) { io.rdData := mem(io.rdAddr) }

  // --------------------------------------------------- \\

}
