package TutorialProblems

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import Literal._
import scala.util.Random

abstract class Filter[T <: Data](dtype: T) extends Module {
  val io = new Bundle {
    val in  = Valid(dtype).asInput
    val out = Valid(dtype).asOutput
} }

class PredicateFilter[T <: Data](dtype: T, f: T => Bool) extends Filter(dtype) {
  io.out.valid := io.in.valid && f(io.in.bits)
  io.out.bits  := io.in.bits
}

object SingleFilter {
  def apply[T <: UInt](dtype: T) = 
    Module(new PredicateFilter(dtype, (x: T) => Bool(false))) // FILL IN FUNCTION
}

object EvenFilter {
  def apply[T <: UInt](dtype: T) = 
    Module(new PredicateFilter(dtype, (x: T) => Bool(false))) // FILL IN FUNCTION
}

class SingleEvenFilter[T <: UInt](dtype: T) extends Filter(dtype) {
  // CREATE COMPOSITION OF SINGLE AND EVEN FILTERS HERE ...
  io.out <> io.in
}

class SingleEvenFilterTests[T <: UInt](c: SingleEvenFilter[T]) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val maxInt  = 1 << 16
    for (i <- 0 until 10) {
      vars.clear()
      val in               = rnd.nextInt(maxInt)
      vars(c.io.in.valid)  = Bool(true)
      vars(c.io.in.bits)   = UInt(in)
      val isSingleEven     = (in <= 9) && (in&1)!=0
      vars(c.io.out.valid) = Bool(isSingleEven)
      vars(c.io.out.bits)  = UInt(in)
      allGood              = step(vars) && allGood
    }
    allGood
  }  
}

