// See LICENSE.txt for license details.
package solutions

import chisel3._
import chisel3.util.log2Up

class DynamicMemorySearch(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val isWr   = Input(Bool())
    val wrAddr = Input(UInt(log2Up(n).W))
    val data   = Input(UInt(w.W))
    val en     = Input(Bool())
    val target = Output(UInt(log2Up(n).W))
    val done   = Output(Bool())
  })
  val index  = Reg(init = 0.asUInt(log2Up(n).W))
  val list   = Mem(n, UInt(w.W))
  val memVal = list(index)
  val over   = !io.en && ((memVal === io.data) || (index === (n-1).asUInt))
  when (io.isWr) {
    list(io.wrAddr) := io.data
  } .elsewhen (io.en) {
    index := 0.U
  } .elsewhen (over === false.B) {
    index := index + 1.U
  }
  io.done   := over
  io.target := index
}
