// See LICENSE.txt for license details.
package problems

import chisel3._

// Example:
//
// This is example of multiplexer 2-to-1 with 'sel' as control signal
// Multiplexed inputs are 'in0' and 'in1'
//
class Mux2 extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(1.W))
    val in0 = Input(UInt(1.W))
    val in1 = Input(UInt(1.W))
    val out = Output(UInt(1.W))
  })
  io.out := (io.sel & io.in1) | (~io.sel & io.in0)
}

// Problem:
//
// Build a 4-to-1 multiplexer out of three 2-to-1 multiplexers
// The first multiplexer is already done for you
//
class Mux4 extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(1.W))
    val in1 = Input(UInt(1.W))
    val in2 = Input(UInt(1.W))
    val in3 = Input(UInt(1.W))
    val sel = Input(UInt(2.W))
    val out = Output(UInt(1.W))
  })

  val m0 = Module(new Mux2())
  m0.io.sel := io.sel(0)
  m0.io.in0 := io.in0
  m0.io.in1 := io.in1

  //Implement below ----------



  // make the compile process happy, needs to be substituted by the output of the Mux
  io.out := 1.U
  //Implement above ----------
}
