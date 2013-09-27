package TutorialExamples

import Chisel._
import scala.collection.mutable.HashMap

class MemorySearch extends Module {
  val io = new Bundle {
    val target  = UInt(INPUT,  4)
    val address = UInt(OUTPUT, 3)
    val en      = Bool(INPUT)
    val done    = Bool(INPUT)
  }
  val index = Reg(init = UInt(0, width = 3))
  val list = Vec(UInt(0), UInt(4), UInt(15), UInt(14), UInt(2), UInt(5), UInt(13)) { UInt(width = 4) }
  val memVal = list(index)

  val done = (memVal === io.target) || (index === UInt(7))

  unless (done === Bool(true)) {
      index := index + UInt(1)
  }
  io.done := done
  io.address := index
}

class MemorySearchTests(c: MemorySearch) extends Tester(c, Array(c.io)) {
  defTests {
     var allGood = true
     val vars = new HashMap[Node, Node]()
     var target = UInt(15)
     var en = Bool(true)
     var addr = UInt(2)
     vars(c.io.en) = en
     vars(c.io.target) = target
     vars(c.io.done) = Bool(false)
     for (i <- 0 until 2) {
     	 vars(c.io.address) = UInt(i)
     	 allGood = step(vars) && allGood     
     }
     vars(c.io.done) = Bool(true)
     vars(c.io.address) = UInt(2)
     allGood = step(vars) && allGood
     allGood = step(vars) && allGood
     allGood = step(vars) && allGood
     allGood = step(vars) && allGood
     allGood     
  }
}
