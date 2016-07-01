// See LICENSE.txt for license details.
package solutions

import Chisel._

class LFSR16 extends Module {
  val io = new Bundle {
    val inc = Bool(INPUT)
    val out = UInt(OUTPUT, 16)
  }
  val res = Reg(init = UInt(1, 16))
  when (io.inc) { 
    val nxt_res = Cat(res(0)^res(2)^res(3)^res(5), res(15,1)) 
    res := nxt_res
  }
  io.out := res
}
