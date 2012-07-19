package Tutorial

import Chisel._
import scala.collection.mutable.HashMap
import scala.util.Random

class Parity extends Component {
  val io = new Bundle {
    val in  = Bool(INPUT)
    val out = Bool(OUTPUT) }
  val s_even :: s_odd :: Nil = Enum(2){ UFix() }
  val state  = Reg(resetVal = s_even)
  when (io.in) {
    when (state === s_even) { state := s_odd  }
    .otherwise              { state := s_even }
  }
  io.out := (state === s_odd)
}

class ParityTests(c: Parity) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    var isOdd   = false
    for (t <- 0 until 10) {
      vars.clear()
      val bit        = rnd.nextInt(2)
      vars(c.io.in)  = Bool(bit == 1)
      vars(c.io.out) = Bool(isOdd)
      isOdd          = if (bit == 1) !isOdd else isOdd
      allGood        = step(vars) && allGood
    }
    allGood
  }
}

