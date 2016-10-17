// See LICENSE.txt for license details.
package examples

import chisel3._

class FullAdder extends Module {
  val io = IO(new Bundle {
    val a    = Input(UInt(width=1))
    val b    = Input(UInt(width=1))
    val cin  = Input(UInt(width=1))
    val sum  = Output(UInt(width=1))
    val cout = Output(UInt(width=1))
  })

  // Generate the sum
  val a_xor_b = io.a ^ io.b
  io.sum := a_xor_b ^ io.cin
  // Generate the carry
  val a_and_b = io.a & io.b
  val b_and_cin = io.b & io.cin
  val a_and_cin = io.a & io.cin
  io.cout := a_and_b | b_and_cin | a_and_cin
}
