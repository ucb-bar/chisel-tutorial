// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement parametrized simple shift register
// 'n' is number of elements in shift register
// 'w' is width of one element

// Implement below ----------
class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })

  io.out := 0.U
}
// Implement above ----------
