package Tutorial

import Chisel._
import scala.collection.mutable.HashMap
import scala.util.Random

class Functional extends Component {
  val io = new Bundle {
    val x   = Bits(16, INPUT)
    val y   = Bits(16, INPUT)
    val z   = Bits(16, OUTPUT)
  }
  def clb(a: Bits, b: Bits, c: Bits, d: Bits) =
    (a & b) | (~c & d)
  io.z := clb(io.x, io.y, io.x, io.y)
}

class FunctionalTests(c: Functional) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars   = new HashMap[Node, Node]()
    val rnd    = new Random()
    val maxInt = 1 << 16
    for (i <- 0 until 10) {
      vars.clear()
      val x = rnd.nextInt(maxInt)
      val y = rnd.nextInt(maxInt)
      vars(c.io.x) = Bits(x)
      vars(c.io.y) = Bits(y)
      // vars(c.io.z) = clb(Bits(x), Bits(y), Bits(x), Bits(y))
      vars(c.io.z) = Bits((x & y) | (~x & y))
      allGood = step(vars) && allGood
    }
    allGood
  }
}
