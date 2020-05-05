// See LICENSE.txt for license details.
package examples

import chisel3._

/**
 * Compute the GCD of 'a' and 'b' using Euclid's algorithm.
 * To start a computation, load the values into 'a' and 'b' and toggle 'load'
 * high.
 * The GCD will be returned in 'out' when 'valid' is high.
 */
class GCD extends Module {
  val io = IO(new Bundle {
    val a     = Input(UInt(16.W))
    val b     = Input(UInt(16.W))
    val load  = Input(Bool())
    val out   = Output(UInt(16.W))
    val valid = Output(Bool())
  })
  val x = Reg(UInt())
  val y = Reg(UInt())

  when (io.load) {
    x := io.a; y := io.b
  } .otherwise {
    when (x > y) {
      x := x - y
    } .elsewhen (x <= y) {
      y := y - x
    }
  }

  io.out := x
  io.valid := y === 0.U
}
