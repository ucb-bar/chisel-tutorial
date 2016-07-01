// See LICENSE.txt for license details.
package problems

import Chisel._

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = new Bundle {
    val in  = UInt(INPUT,  w)
    val out = UInt(OUTPUT, w)
  }
  /// fill in here ...
  io.out := 0.U
}
