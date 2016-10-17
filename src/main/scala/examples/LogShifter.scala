// See LICENSE.txt for license details.
package examples

import chisel3._

class LogShifter extends Module {
  val io = IO(new Bundle {
    val in    = Input(UInt(width=16))
    val shamt = Input(UInt(width=4))
    val out   = Output(UInt(width=16))
  })
  val s0 = Reg(init = UInt(0, 16))
  when (io.shamt(3) === UInt(1)) {
    s0 := io.in << UInt(8)
  } .otherwise {
    s0 := io.in
  }
  val s1 = Reg(init = UInt(0, 16))
  when (io.shamt(2) === UInt(1)) {
    s1 := s0 << UInt(4)
  } .otherwise {
    s1 := s0
  }
  val s2 = Reg(init = UInt(0, 16))
  when (io.shamt(1) === UInt(1)) {
    s2 := s1 << UInt(2)
  } .otherwise {
    s2 := s1
  }
  when (io.shamt(1) === UInt(1)) {
    io.out := s2 << UInt(1)
  } .otherwise {
    io.out := s2
  }
}
