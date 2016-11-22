// See LICENSE.txt for license details.
package solutions

import chisel3._
import chisel3.util.Cat

class LFSR16 extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val out = Output(UInt(16.W))
  })

  val res = Reg(init = 1.U(16.W))
  when (io.inc) { 
    val nxt_res = Cat(res(0)^res(2)^res(3)^res(5), res(15,1)) 
    res := nxt_res
  }
  io.out := res
}
