// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a test for this module. Please edit:
// .../chisel_tutorial/src/test/scala/problems/Max2Tests.scala
//
class Max2 extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(8.W))
    val in1 = Input(UInt(8.W))
    val out = Output(UInt(8.W))
  })
  io.out := Mux(io.in0 > io.in1, io.in0, io.in1)
}
