// See LICENSE.txt for license details.
package examples

import chisel3._

class Tbl extends Module {
  val io = IO(new Bundle {
    val addr = Input(UInt(8.W))
    val out  = Output(UInt(8.W))
  })
  val r = VecInit(Range(0, 256).map(_.asUInt(8.W)))
  io.out := r(io.addr)
}
