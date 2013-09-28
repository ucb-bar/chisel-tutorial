package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

//A 4-bit adder with carry in and carry out
class HiLoMultiplier() extends Module {
  val io = new Bundle {
    val A  = UInt(INPUT, 16)
    val B  = UInt(INPUT, 16)
    val Hi = UInt(OUTPUT, 16)
    val Lo = UInt(OUTPUT, 16)
  }
  val mult = io.A * io.B
  io.Lo := mult(15, 0)
  io.Hi := mult(31, 16)
}

class HiLoMultiplierTests(c: HiLoMultiplier) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val rnd = new Random()
    val vars = new HashMap[Node, Node]()
    for (t <- 0 until 4) {
      vars.clear()
      val rnd0 = rnd.nextInt(65535)
      val rnd1 = rnd.nextInt(65535)
      val ref_out = UInt(rnd0 * rnd1, width=32)
      val hi_ref = ref_out(31, 16)
      val lo_ref = ref_out(15, 0)
      vars(c.io.A) = UInt(rnd0)
      vars(c.io.B) = UInt(rnd1)
      vars(c.io.Lo) = lo_ref
      vars(c.io.Hi) = hi_ref
      allGood = step(vars) && allGood
    }
    allGood
  }
}
