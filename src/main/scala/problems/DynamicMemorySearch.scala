// See LICENSE.txt for license details.
package problems

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
  val memVal = 0.U
  val done   = !io.en && ((memVal === io.data) || (index === UInt(n-1)))
  // ...
  when (io.en) {
    index := 0.U
  } .elsewhen (done === Bool(false)) {
    index := index + 1.U
  }
  io.done   := done
  io.target := index
}
