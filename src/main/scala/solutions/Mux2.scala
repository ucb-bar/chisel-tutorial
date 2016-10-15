// See LICENSE.txt for license details.
package solutions

import chisel3._

class Mux2 extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(width = 1))
    val in0 = Input(UInt(width = 1))
    val in1 = Input(UInt(width = 1))
    val out = Output(UInt(width = 1))
  })
  io.out := (io.sel & io.in1) | (~io.sel & io.in0)
}
