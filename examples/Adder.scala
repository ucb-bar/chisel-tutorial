package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

//A n-bit adder with carry in and carry out
class Adder(val n:Int) extends Module {
  val io = new Bundle {
    val A    = UInt(INPUT, n)
    val B    = UInt(INPUT, n)
    val Cin  = UInt(INPUT, 1)
    val Sum  = UInt(OUTPUT, n)
    val Cout = UInt(OUTPUT, 1)
  }
  //create a vector of FullAdders
  val FAs   = Vec.fill(n){ Module(new FullAdder()).io }
  val carry = Vec.fill(n+1){ UInt(width = 1) }
  val sum   = Vec.fill(n){ Bool() }

  //first carry is the top level carry in
  carry(0) := io.Cin

  //wire up the ports of the full adders
  for (i <- 0 until n) {
    FAs(i).a := io.A(i)
    FAs(i).b := io.B(i)
    FAs(i).cin := carry(i)
    carry(i+1) := FAs(i).cout
    sum(i) := FAs(i).sum.toBool()
  }
  io.Sum := sum.toBits().toUInt()
  io.Cout := carry(n)
}

class AdderTests(c: Adder) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val rnd = new Random()
    val vars = new HashMap[Node, Node]()

    for (t <- 0 until 4) {
      vars.clear()
      val rnd0 = rnd.nextInt(c.n)
      val rnd1 = rnd.nextInt(c.n)
      val rnd2 = rnd.nextInt(1)

      vars(c.io.A) = UInt(rnd0)
      vars(c.io.B) = UInt(rnd1)
      vars(c.io.Cin) = UInt(rnd2)
      val rsum = UInt(rnd0 + rnd1 + rnd2, width=c.n + 1)
      vars(c.io.Sum) = rsum(c.n - 1, 0)
      vars(c.io.Cout) = rsum(c.n)
      allGood = step(vars) && allGood
    }
    allGood
  }
}
