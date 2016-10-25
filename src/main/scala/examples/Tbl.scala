// See LICENSE.txt for license details.
package examples

import chisel3._

class Tbl extends Module {
  val io = IO(new Bundle {
    val addr = Input(UInt(width= 8))
    val out  = Output(UInt(width=8))
  })
  val r = Wire(init = Vec(Range(0, 256).map(UInt(_, width = 8))))
  io.out := r(io.addr)
}
