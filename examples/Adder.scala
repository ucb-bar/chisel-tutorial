package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class Adder4Wrapper() extends Module {
  val io = new Bundle {
    val A    = UInt(INPUT, 4)
    val B    = UInt(INPUT, 4)
    val Cin  = UInt(INPUT, 1)
    val Sum  = UInt(OUTPUT, 4)
    val Cout = UInt(OUTPUT, 1)
  }
  val param_adder = new Adder(n = 4)
  param_adder.io.A := io.A
  param_adder.io.B := io.B
  param_adder.io.Cin := io.Cin
  io.Sum := param_adder.io.Sum
  io.Cout := param_adder.io.Cout
}

//A n-bit adder with carry in and carry out
class Adder(n:Int) extends Module {
  val io = new Bundle {
    val A    = UInt(INPUT, n)
    val B    = UInt(INPUT, n)
    val Cin  = UInt(INPUT, 1)
    val Sum  = UInt(OUTPUT, n)
    val Cout = UInt(OUTPUT, 1)
  }
  //create a vector of FullAdders
  val FAs   = Vec.fill(n){ new FullAdder().io }
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

class AdderTests(c: Adder4Wrapper) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val rnd = new Random()
    val vars = new HashMap[Node, Node]()

    for (t <- 0 until 4) {
      vars.clear()
      val rnd0 = rnd.nextInt(16)
      val rnd1 = rnd.nextInt(16)
      val rnd2 = rnd.nextInt(2)
	
      vars(c.io.A) = UInt(rnd0)
      vars(c.io.B) = UInt(rnd1)
      vars(c.io.Cin) = UInt(rnd2)
      val rsum = UInt(rnd0 & 0xF) + UInt(rnd1 & 0xF) + UInt(rnd2 & 0x1)
      vars(c.io.Sum) = rsum(3,0)
      vars(c.io.Cout) = rsum(4)
      allGood = step(vars) && allGood
    }
    allGood
  }
}
