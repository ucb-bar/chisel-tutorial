// See LICENSE.txt for license details.
package examples

import chisel3._

class Functionality extends Module {
  val io = IO(new Bundle {
    val x   = Input(Bits(width= 16))
    val y   = Input(Bits(width= 16))
    val z   = Output(Bits(width=16))
  })
  def clb(a: UInt, b: UInt, c: UInt, d: UInt) =
    (a & b) | (~c & d)
  io.z := clb(io.x, io.y, io.x, io.y)
}
