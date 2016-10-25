// See LICENSE.txt for license details.
package solutions

import chisel3._
import chisel3.util.log2Up

class DynamicMemorySearch(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val isWr   = Input(Bool())
    val wrAddr = Input(UInt(width = log2Up(n)))
    val data   = Input(UInt(width = w))
    val en     = Input(Bool())
    val target = Output(UInt(width = log2Up(n)))
    val done   = Output(Bool())
  })
  val index  = Reg(init = UInt(0, width = log2Up(n)))
  val list   = Mem(n, UInt(width = w))
  val memVal = list(index)
  val over   = !io.en && ((memVal === io.data) || (index === UInt(n-1)))
  when (io.isWr) {
    list(io.wrAddr) := io.data
  } .elsewhen (io.en) {
    index := 0.U
  } .elsewhen (over === Bool(false)) {
    index := index + 1.U
  }
  io.done   := over
  io.target := index
}
