// See LICENSE.txt for license details.
package examples

import chisel3._

class Darken extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(8.W))
    val out = Output(UInt(8.W))
  })

  io.out := io.in << 1.U
}
