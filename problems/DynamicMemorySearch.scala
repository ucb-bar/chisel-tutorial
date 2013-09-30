package TutorialProblems

import Chisel._
import util.Random
import scala.collection.mutable.HashMap

class DynamicMemorySearch extends Module {
  val io = new Bundle {
    val isWr   = Bool(INPUT)
    val wrAddr = UInt(INPUT, 3)
    val data   = UInt(INPUT, 4)
    val en     = Bool(INPUT)
    val target = UInt(OUTPUT, 3)
    val done   = Bool(OUTPUT)
  }
  val index  = Reg(init = UInt(0, width = 3))
  val memVal = UInt(0) // TODO REPLACE HERE WITH MEM
  val done   = !io.en && ((memVal === io.data) || (index === UInt(7)))
  when (io.en) {
    index := UInt(0)
  } .elsewhen (done === Bool(false)) {
    index := index + UInt(1)
  }
  io.done   := done
  io.target := index
}

class DynamicMemorySearchTests(c: DynamicMemorySearch) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val rnd   = new Random()
    val ivars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    val list  = Array.fill(8){ 0 }
    for (k <- 0 until 16) {
      // WRITE A WORD
      ivars(c.io.en)      = Bool(false)
      ivars(c.io.isWr)    = Bool(true)
      val wrAddr          = rnd.nextInt(8-1)
      val data            = rnd.nextInt(16)
      ivars(c.io.wrAddr)  = UInt(wrAddr)
      ivars(c.io.data)    = UInt(data)
      step(ivars, ovars)
      list(wrAddr)        = data
      // SETUP SEARCH
      val target          = rnd.nextInt(16)
      ivars(c.io.isWr)    = Bool(false)
      ivars(c.io.data)    = UInt(target)
      ivars(c.io.en)      = Bool(true)
      step(ivars, ovars)
      do {
        ivars(c.io.en) = Bool(false)
        step(ivars, ovars)
      } while (ovars(c.io.done).litValue() == 0)
      val addr = ovars(c.io.target).litValue()
      println("LOOKING FOR " + target + " FOUND " + addr.toInt)
      allGood = addr == (list.length-1) || list(addr.toInt) == target
    }
    allGood
  }
}
