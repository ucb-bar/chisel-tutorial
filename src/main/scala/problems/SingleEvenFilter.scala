// See LICENSE.txt for license details.
package problems

import Chisel._

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
