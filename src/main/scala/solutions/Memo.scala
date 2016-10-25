// See LICENSE.txt for license details.
package solutions

import chisel3._

class Memo extends Module {
  val io = IO(new Bundle {
    val wen     = Input(Bool())
    val wrAddr  = Input(UInt(width = 8))
    val wrData  = Input(UInt(width = 8))
    val ren     = Input(Bool())
    val rdAddr  = Input(UInt(width = 8))
    val rdData  = Output(UInt(width = 8))
  })

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
