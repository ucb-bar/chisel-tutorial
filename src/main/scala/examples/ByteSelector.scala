// See LICENSE.txt for license details.
package examples

import chisel3._

class ByteSelector extends Module {
  val io = IO(new Bundle {
    val in     = Input(UInt(width=32))
    val offset = Input(UInt(width=2))
    val out    = Output(UInt(width=8))
  })
  io.out := UInt(0, width=8)
  when (io.offset === UInt(0, width=2)) {
    io.out := io.in(7,0)
  } .elsewhen (io.offset === 1.U) {
    io.out := io.in(15,8)
  } .elsewhen (io.offset === 2.U) {
    io.out := io.in(23,16)
  } .otherwise {
    io.out := io.in(31,24)
  }
}
