// See LICENSE.txt for license details.
package solutions

import chisel3._

class MaxN(val n: Int, val w: Int) extends Module {

  private def Max2(x: UInt, y: UInt) = Mux(x > y, x, y)

  val io = IO(new Bundle {
    val ins = Input(Vec(n, UInt(width = w)))
    val out = Output(UInt(width = w))
  })
  io.out := io.ins.reduceLeft(Max2)
}
