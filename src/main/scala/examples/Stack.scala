// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.util.log2Up

import scala.collection.mutable.HashMap
import scala.collection.mutable.{Stack => ScalaStack}
import scala.util.Random

class Stack(val depth: Int) extends Module {
  val io = IO(new Bundle {
    val push    = Input(Bool())
    val pop     = Input(Bool())
    val en      = Input(Bool())
    val dataIn  = Input(UInt(32.W))
    val dataOut = Output(UInt(32.W))
  })

  val stack_mem = Mem(depth, UInt(32.W))
  val sp        = Reg(init = 0.U(log2Up(depth+1).W))
  val out       = Reg(init = 0.U(32.W))

  when (io.en) {
    when(io.push && (sp < depth.asUInt)) {
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
