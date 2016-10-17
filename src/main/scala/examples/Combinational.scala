// See LICENSE.txt for license details.
package examples

import chisel3._

class Combinational extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(width= 16))
    val y   = Input(UInt(width= 16))
    val z   = Output(UInt(width=16))
  })
  io.z := io.x + io.y
}
