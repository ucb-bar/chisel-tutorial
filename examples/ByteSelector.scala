package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class ByteSelector extends Module {
  val io = new Bundle {
    val in     = UInt(INPUT, 32)
    val offset = UInt(INPUT, 2)
    val out    = UInt(OUTPUT, 8)
  }
  io.out := UInt(0, 8)
  when (io.offset === UInt(0)) {
    io.out := io.in(7,0)
  } .elsewhen (io.offset === UInt(1)) {
    io.out := io.in(15,8)
  } .elsewhen (io.offset === UInt(2)) {
    io.out := io.in(23,16)
  } .otherwise {
    io.out := io.in(31,24)
  }    
}

class ByteSelectorTests(c: ByteSelector) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars = new HashMap[Node, Node]()
    val test_in = UInt(12345678)
    for (t <- 0 until 4) {
      vars(c.io.in) = test_in
      vars(c.io.offset) = UInt(t)
      val ref_out = (test_in >> (UInt(t * 8))) & UInt(0xFF)
      vars(c.io.out) = ref_out
      allGood = step(vars) && allGood
    }
    allGood
  }
}
