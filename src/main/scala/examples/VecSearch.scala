// See LICENSE.txt for license details.
package examples

import chisel3._

object VecSearchTest {
  val pattern = Array(0, 4, 15, 14, 2, 5, 13)
}

class VecSearch extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(4.W))
  })
  val index = RegInit(0.U(3.W))
  val elts  = VecInit(VecSearchTest.pattern.map(_.asUInt(4.W)))
  index := index + 1.U
  io.out := elts(index)
}
