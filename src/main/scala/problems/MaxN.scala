// See LICENSE.txt for license details.
package problems

import Chisel._

class MaxN(val n: Int, val w: Int) extends Module {

  private def Max2(x: UInt, y: UInt) = Mux(x > y, x, y)

  val io = new Bundle {
    val ins = Vec(n,  UInt(INPUT, w) )
    val out = UInt(OUTPUT, w)
  }
  io.out := io.ins.reduceLeft(Max2)
}
