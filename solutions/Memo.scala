package TutorialSolutions

import Chisel._
import Node._
import scala.collection.mutable.HashMap

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

  // write
  when (io.wen) { mem(io.wrAddr) := io.wrData }
  
  // read
  io.rdData := UInt(0)
  when (io.ren) { io.rdData := mem(io.rdAddr) }

  // --------------------------------------------------- \\

}

class MemoTests(c: Memo) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    def rd(addr: UInt, data: UInt) = {
      vars.clear()
      vars(c.io.ren)   = Bool(true)
      vars(c.io.rdAddr) = addr
      vars(c.io.rdData) = data
      step(vars)
    }
    def wr(addr: UInt, data: UInt)  = {
      vars.clear()
      vars(c.io.wen)   = Bool(true)
      vars(c.io.wrAddr) = addr
      vars(c.io.wrData) = data
      step(vars)
    }
    allGood = wr(UInt(0), UInt(1))  && allGood
    allGood = rd(UInt(0), UInt(1))  && allGood
    allGood = wr(UInt(9), UInt(11)) && allGood
    allGood = rd(UInt(9), UInt(11)) && allGood
    allGood
  }
}
