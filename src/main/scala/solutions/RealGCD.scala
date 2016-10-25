// See LICENSE.txt for license details.
package solutions

import chisel3._
import chisel3.util.{Valid, DeqIO}

class RealGCDInput extends Bundle {
  val a = UInt(width = 16)
  val b = UInt(width = 16)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(width = 16)))
  })

  val x = Reg(UInt())
  val y = Reg(UInt())
  val p = Reg(init=Bool(false))

  io.in.ready := !p

  when (io.in.valid && !p) {
    x := io.in.bits.a
    y := io.in.bits.b
    p := Bool(true)
  } 

  when (p) {
    when (x > y)  { x := y; y := x } 
    .otherwise    { y := y - x }
  }

  io.out.bits  := x
  io.out.valid := y === Bits(0) && p
  when (io.out.valid) {
    p := Bool(false)
  }
}
