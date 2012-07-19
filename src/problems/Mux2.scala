package Tutorial

import Chisel._
import scala.math._
import scala.collection.mutable.HashMap

class Mux2 extends Component {
  val io = new Bundle {
    val sel = Bits(INPUT,  1)
    val in0 = Bits(INPUT,  1)
    val in1 = Bits(INPUT,  1)
    val out = Bits(OUTPUT, 1)
  }
  io.out := (io.sel & io.in1) | (~io.sel & io.in0)
}

class Mux2Tests(c: Mux2) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val n = pow(2, 3).toInt
    val vars = new HashMap[Node, Node]()
    for (s <- 0 until 2) {
      for (i0 <- 0 until 2) {
        for (i1 <- 0 until 2) {
          vars(c.io.sel) = Bits(s)
          vars(c.io.in1) = Bits(i1)
          vars(c.io.in0) = Bits(i0)
          vars(c.io.out) = Bits(if (s == 1) i1 else i0)
          allGood = step(vars) && allGood
        }
      }
    }
    allGood
  }
}
