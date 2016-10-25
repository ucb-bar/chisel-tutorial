// See LICENSE.txt for license details.
package examples

import chisel3._

//A 4-bit adder with carry in and carry out
class HiLoMultiplier() extends Module {
  val io = IO(new Bundle {
    val A  = Input(UInt(width=16))
    val B  = Input(UInt(width=16))
    val Hi = Output(UInt(width=16))
    val Lo = Output(UInt(width=16))
  })
  val mult = io.A * io.B
  io.Lo := mult(15, 0)
  io.Hi := mult(31, 16)
}
