// See LICENSE.txt for license details.
package TutorialSolutions

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
  io.rdData := UInt(0)
  when (io.ren) { io.rdData := mem(io.rdAddr) }

  // --------------------------------------------------- \\

}

class MemoTests(c: Memo) extends Tester(c) {
  def rd(addr: Int, data: Int) = {
    poke(c.io.ren, 1)
    poke(c.io.rdAddr, addr)
    step(1)
    expect(c.io.rdData, data)
  }
  def wr(addr: Int, data: Int)  = {
    poke(c.io.wen,    1)
    poke(c.io.wrAddr, addr)
    poke(c.io.wrData, data)
    step(1)
  }
  wr(0, 1)
  rd(0, 1)
  wr(9, 11)
  rd(9, 11)
}
