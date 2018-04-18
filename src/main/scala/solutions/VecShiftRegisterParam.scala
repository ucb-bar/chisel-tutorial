// See LICENSE.txt for license details.
package solutions

import chisel3._

// Problem:
//
// Implement a parametrized simple shift register.
// 'n' is the number of elements in the shift register.
// 'w' is the width of one element.

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })

  val initValues = Seq.fill(n) { 0.U(w.W) }
  val delays = RegInit(VecInit(initValues))

  for (i <- n-1 to 1 by -1) {
    delays(i) := delays(i - 1)
  }

  delays(0) := io.in
  io.out := delays(n-1)
}
