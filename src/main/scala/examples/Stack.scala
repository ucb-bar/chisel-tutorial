// See LICENSE.txt for license details.
package examples

import Chisel._

import scala.collection.mutable.HashMap
import scala.collection.mutable.{Stack => ScalaStack}
import scala.util.Random

class Stack(val depth: Int) extends Module {
  val io = new Bundle {
    val push    = Bool(INPUT)
    val pop     = Bool(INPUT)
    val en      = Bool(INPUT)
    val dataIn  = UInt(INPUT,  32)
    val dataOut = UInt(OUTPUT, 32)
  }

  val stack_mem = Mem(depth, UInt(width = 32))
  val sp        = Reg(init = UInt(0, width = log2Up(depth+1)))
  val out       = Reg(init = UInt(0, width = 32))

  when (io.en) {
    when(io.push && (sp < UInt(depth))) {
      stack_mem(sp) := io.dataIn
      sp := sp + 1.U
    } .elsewhen(io.pop && (sp > 0.U)) {
      sp := sp - 1.U
    }
    when (sp > 0.U) {
      out := stack_mem(sp - 1.U)
    }
  }

  io.dataOut := out
}
