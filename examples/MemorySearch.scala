package TutorialExamples

import Chisel._
import util.Random
import scala.collection.mutable.HashMap

class MemorySearch extends Module {
  val io = new Bundle {
    val target  = UInt(INPUT,  4)
    val en      = Bool(INPUT)
    val done    = Bool(OUTPUT)
    val address = UInt(OUTPUT, 3)
  }
  val index = Reg(init = UInt(0, width = 3))
  val list  = Vec(UInt(0), UInt(4), UInt(15), UInt(14),
                  UInt(2), UInt(5), UInt(13)){ UInt(width = 4) }
  val memVal = list(index)
  val done = !io.en && ((memVal === io.target) || (index === UInt(7)))
  when (io.en) {
    index := UInt(0)
  } .elsewhen (done === Bool(false)) {
    index := index + UInt(1)
  }
  io.done    := done
  io.address := index
}

class MemorySearchTests(c: MemorySearch) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val rnd   = new Random()
    val ivars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    val list = Array(0, 4, 15, 14, 2, 5, 13)
    for (k <- 0 until 16) {
      val target = rnd.nextInt(16)
      ivars(c.io.en)      = Bool(true)
      ivars(c.io.target)  = UInt(target)
      step(ivars, ovars)
      do {
        ivars(c.io.en) = Bool(false)
        step(ivars, ovars)
      } while (ovars(c.io.done).litValue() == 0)
      val addr = ovars(c.io.address).litValue()
      println("LOOKING FOR " + target + " FOUND " + addr.toInt)
      allGood = addr == list.length || list(addr.toInt) == target
    }
    allGood
  }
}
