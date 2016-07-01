// See LICENSE.txt for license details.
package examples

import Chisel._


object VecSearchTest {
  val pattern = Array(0, 4, 15, 14, 2, 5, 13)
}

class VecSearch extends Module {
  val io = new Bundle {
    val out = UInt(OUTPUT,  4)
  }
  val index = Reg(init = UInt(0, width = 3))
  val elts  = Wire(init = Vec(VecSearchTest.pattern.map(UInt(_, 4))))
  index := index + 1.U
  io.out := elts(index)
}
