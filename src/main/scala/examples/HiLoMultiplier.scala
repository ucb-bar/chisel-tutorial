// See LICENSE.txt for license details.
package examples

import chisel3._

//A 16*16-bit multiplier with separate high and low product outputs
class HiLoMultiplier() extends Module {
  val io = IO(new Bundle {
    val A  = Input(UInt(16.W))
    val B  = Input(UInt(16.W))
    val Hi = Output(UInt(16.W))
    val Lo = Output(UInt(16.W))
  })
  val mult = io.A * io.B
  io.Lo := mult(15, 0)
  io.Hi := mult(31, 16)
}
