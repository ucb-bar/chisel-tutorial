// See LICENSE.txt for license details.
package examples

import chisel3._

class Combinational extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(16.W))
    val y   = Input(UInt(16.W))
    val z   = Output(UInt(16.W))
  })
  io.z := io.x + io.y
}
