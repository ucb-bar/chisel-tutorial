package TutorialSolutions

import Chisel._
import Node._
import Counter._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Counter {

  def wrapAround(n: UFix, max: UFix) = 
    Mux(n > max, UFix(0), n)

  // ---------------------------------------- \\
  // Modify this function to increment by the
  // amt only when en is asserted
  // ---------------------------------------- \\

  def counter(max: UFix, en: Bool, amt: UFix) = {
    val x = Reg(resetVal = UFix(0, max.getWidth))
    when (en) { x := wrapAround(x + amt, max) }
    x
  }

  // ---------------------------------------- \\

}

class Counter extends Component {
  val io = new Bundle {
    val inc = Bool(INPUT)
    val amt = UFix(INPUT, 4)
    val tot = UFix(OUTPUT, 8)
  }

  io.tot := counter(UFix(255), io.inc, io.amt)

}

class CounterTest(c: Counter) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val maxInt  = 16
    var curCnt  = 0

    def intWrapAround(n: Int, max: Int) = 
      if(n > max) 0 else n
        
    // let it spin for a bit
    for (i <- 0 until 5) {
      step(vars, isTrace = false)
    }

    for (i <- 0 until 10) {
      val inc = rnd.nextBoolean()
      val amt = rnd.nextInt(maxInt)
      vars(c.io.inc) = Bool(inc)
      vars(c.io.amt) = UFix(amt)
      vars(c.io.tot) = UFix(curCnt)
      allGood = step(vars) && allGood      
      curCnt = if(inc) intWrapAround(curCnt + amt, 255) else curCnt
    }
    allGood

  }
}
