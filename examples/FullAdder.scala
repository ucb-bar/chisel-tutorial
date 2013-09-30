package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class FullAdder extends Module {
  val io = new Bundle {
    val a    = UInt(INPUT, 1)
    val b    = UInt(INPUT, 1)
    val cin  = UInt(INPUT, 1)
    val sum  = UInt(OUTPUT, 1)
    val cout = UInt(OUTPUT, 1)
  }

  //Generate the sum
  val a_xor_b = io.a ^ io.b
  io.sum := a_xor_b ^ io.cin
  //Generate the carry
  val a_and_b = io.a & io.b
  val b_and_cin = io.b & io.cin
  val a_and_cin = io.a & io.cin
  io.cout := a_and_b | b_and_cin | a_and_cin
}

class FullAdderTests(c: FullAdder) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val rnd  = new Random()
    val vars = new HashMap[Node, Node]()
    for (t <- 0 until 4) {
      vars.clear()
      val a    = rnd.nextInt(2)
      val b    = rnd.nextInt(2)
      val cin  = rnd.nextInt(2)
      val res  = a + b + cin
      val sum  = res & 1
      val cout = (res >> 1) & 1
      vars(c.io.a)    = UInt(a)
      vars(c.io.b)    = UInt(b)
      vars(c.io.cin)  = UInt(cin)
      vars(c.io.sum)  = UInt(sum)
      vars(c.io.cout) = UInt(cout)
      allGood = step(vars) && allGood
    }
    allGood
  }
}
