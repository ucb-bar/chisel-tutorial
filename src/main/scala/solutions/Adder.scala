// See LICENSE.txt for license details.
package solutions

import chisel3._

// Problem:
//
// 'out' should be the sum of 'in0' and 'in1'
// Adder width should be parametrized
//
class Adder(val w: Int) extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(w.W))
    val in1 = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })
  io.out := io.in0 + io.in1
}
