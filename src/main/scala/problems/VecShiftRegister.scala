// See LICENSE.txt for license details.
package problems

import chisel3._

class VecShiftRegister extends Module {
  val io = IO(new Bundle {
    val ins   = Input(Vec(4, UInt(4.W)))
    val load  = Input(Bool())
    val shift = Input(Bool())
    val out   = Output(UInt(4.W))
  })
  // FILL IN LOADABLE SHIFT REGISTER USING VEC 
  io.out := 0.U
}
