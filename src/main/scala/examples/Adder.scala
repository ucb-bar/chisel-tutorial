// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.util._

//A n-bit adder with carry in and carry out
class Adder(val n:Int) extends Module {
  val io = IO(new Bundle {
    val A    = Input(UInt(width=n))
    val B    = Input(UInt(width=n))
    val Cin  = Input(UInt(width=1))
    val Sum  = Output(UInt(width=n))
    val Cout = Output(UInt(width=1))
  })
  //create a vector of FullAdders
  val FAs   = Vec.fill(n)(Module(new FullAdder()).io)
  val carry = Wire(Vec(n+1, UInt(width = 1)))
  val sum   = Wire(Vec(n, Bool()))

  //first carry is the top level carry in
  carry(0) := io.Cin

  //wire up the ports of the full adders
  for (i <- 0 until n) {
    FAs(i).a := io.A(i)
    FAs(i).b := io.B(i)
    FAs(i).cin := carry(i)
    carry(i+1) := FAs(i).cout
    sum(i) := FAs(i).sum.toBool()
  }
  io.Sum := Cat(sum.reverse)
  io.Cout := carry(n)
}
