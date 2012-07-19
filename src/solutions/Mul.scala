package Tutorial

import Chisel._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Mul extends Component {
  val io = new Bundle {
    val x   = UFix(INPUT,  4)
    val y   = UFix(INPUT,  4)
    val z   = UFix(OUTPUT, 8)
  }
  val muls = new ArrayBuffer[UFix]()

  // -------------------------------- \\
  // Calculate io.z = io.x * io.y by
  // building filling out muls
  // -------------------------------- \\


  // -------------------------------- \\
}

class MulTests(c: Mul) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val maxInt  = 1 << 4
    for (i <- 0 until 10) {
      val x = rnd.nextInt(maxInt)
      val y = rnd.nextInt(maxInt)
      vars(c.io.x) = UFix(x)
      vars(c.io.y) = UFix(y)
      vars(c.io.z) = UFix(x * y)
      allGood = step(vars) && allGood
    }
    allGood
  }
}
