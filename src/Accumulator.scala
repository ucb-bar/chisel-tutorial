package Tutorial 

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class Accumulator extends Component {
  val io = new Bundle {
    val in  = UFix(INPUT,  1)
    val out = UFix(OUTPUT, 8)
  }

  // ----------------------------------------------- \\
  // Accumulate in
  // Shouldn't need more than 3 lines of code
  // ----------------------------------------------- \\





  // ----------------------------------------------- \\


}

class AccumulatorTests(c: Accumulator) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    var tot     = 0
    for (t <- 0 until 16) {
      vars.clear()
      val in         = rnd.nextInt(2) == 1
      vars(c.io.in)  = Bool(in)
      vars(c.io.out) = UFix(tot)
      allGood        = step(vars) && allGood
      if (in) tot += 1
    }
    allGood
  }
}
