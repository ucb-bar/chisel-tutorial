// See LICENSE.txt for license details.
package examples

import chisel3._

class Darken extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(width=8))
    val out = Output(UInt(width=8))
  })

  io.out := io.in << 1.U
}
