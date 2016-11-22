// See LICENSE.txt for license details.
package examples

import chisel3._

class Functionality extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(16.W))
    val y   = Input(UInt(16.W))
    val z   = Output(UInt(16.W))
  })
  def clb(a: UInt, b: UInt, c: UInt, d: UInt) =
    (a & b) | (~c & d)
  io.z := clb(io.x, io.y, io.x, io.y)
}
