// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.util._

//A 4-bit adder with carry in and carry out
class Adder4 extends Module {
  val io = IO(new Bundle {
    val A    = Input(UInt(4.W))
    val B    = Input(UInt(4.W))
    val Cin  = Input(UInt(1.W))
    val Sum  = Output(UInt(4.W))
    val Cout = Output(UInt(1.W))
  })
  //Adder for bit 0
  val Adder0 = Module(new FullAdder())
  Adder0.io.a := io.A(0)
  Adder0.io.b := io.B(0)
  Adder0.io.cin := io.Cin
  val s0 = Adder0.io.sum
  //Adder for bit 1
  val Adder1 = Module(new FullAdder())
  Adder1.io.a := io.A(1)
  Adder1.io.b := io.B(1)
  Adder1.io.cin := Adder0.io.cout
  val s1 = Cat(Adder1.io.sum, s0)
  //Adder for bit 2
  val Adder2 = Module(new FullAdder())
  Adder2.io.a := io.A(2)
  Adder2.io.b := io.B(2)
  Adder2.io.cin := Adder1.io.cout
  val s2 = Cat(Adder2.io.sum, s1)
  //Adder for bit 3
  val Adder3 = Module(new FullAdder())
  Adder3.io.a := io.A(3)
  Adder3.io.b := io.B(3)
  Adder3.io.cin := Adder2.io.cout
  io.Sum := Cat(Adder3.io.sum, s2).asUInt
  io.Cout := Adder3.io.cout
}
