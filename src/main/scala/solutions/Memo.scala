// See LICENSE.txt for license details.
package solutions

import chisel3._

class Memo extends Module {
  val io = IO(new Bundle {
    val wen     = Input(Bool())
    val wrAddr  = Input(UInt(8.W))
    val wrData  = Input(UInt(8.W))
    val ren     = Input(Bool())
    val rdAddr  = Input(UInt(8.W))
    val rdData  = Output(UInt(8.W))
  })

  val mem = Mem(256, UInt(8.W))

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
