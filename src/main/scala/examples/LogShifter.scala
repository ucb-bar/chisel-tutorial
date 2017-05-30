// See LICENSE.txt for license details.
package examples

import chisel3._

class LogShifter extends Module {
  val io = IO(new Bundle {
    val in    = Input(UInt(16.W))
    val shamt = Input(UInt(4.W))
    val out   = Output(UInt(16.W))
  })
  val s0 = RegInit(0.U(16.W))
  when (io.shamt(3) === 1.U) {
    s0 := io.in << 8.U
  } .otherwise {
    s0 := io.in
  }
  val s1 = RegInit(0.U(16.W))
  when (io.shamt(2) === 1.U) {
    s1 := s0 << 4.U
  } .otherwise {
    s1 := s0
  }
  val s2 = RegInit(0.U(16.W))
  when (io.shamt(1) === 1.U) {
    s2 := s1 << 2.U
  } .otherwise {
    s2 := s1
  }
  when (io.shamt(1) === 1.U) {
    io.out := s2 << 1.U
  } .otherwise {
    io.out := s2
  }
}
