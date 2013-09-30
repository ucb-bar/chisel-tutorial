package TutorialSolutions

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
    Module(new PredicateFilter(dtype, (x: T) => x <= UInt(9)))
}

object EvenFilter {
  def apply[T <: UInt](dtype: T) = 
    Module(new PredicateFilter(dtype, (x: T) => x(0).toBool))
}

class SingleEvenFilter[T <: UInt](dtype: T) extends Filter(dtype) {
  val single = SingleFilter(dtype)
  val even   = EvenFilter(dtype)
  io.in         <> single.io.in
  single.io.out <> even.io.in
  even.io.out   <> io.out
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
      val isSingleEven     = (in <= 9) && ((in&1)!=0)
      vars(c.io.out.valid) = Bool(isSingleEven)
      vars(c.io.out.bits)  = UInt(in)
      allGood              = step(vars) && allGood
    }
    allGood
  }  
}

