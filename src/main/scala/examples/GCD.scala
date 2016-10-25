// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.util.unless

class GCD extends Module {
  val io = IO(new Bundle {
    val a  = Input(UInt(width= 16))
    val b  = Input(UInt(width= 16))
    val e  = Input(Bool())
    val z  = Output(UInt(width=16))
    val v  = Output(Bool())
  })
  val x  = Reg(UInt())
  val y  = Reg(UInt())
  when   (x > y) { x := x - y }
  unless (x > y) { y := y - x }
  when (io.e) { x := io.a; y := io.b }
  io.z := x
  io.v := y === 0.U
}
