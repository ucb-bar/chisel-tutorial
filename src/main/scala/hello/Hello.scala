// See LICENSE.txt for license details.
package hello

import chisel3._
import chisel3.iotesters.{PeekPokeTester, Driver}

class Hello extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(8.W))
  })
  io.out := 42.U
}

class HelloTests(c: Hello) extends PeekPokeTester(c) {
  step(1)
  expect(c.io.out, 42)
}

object Hello {
  def main(args: Array[String]): Unit = {
    if (!Driver(() => new Hello())(c => new HelloTests(c))) System.exit(1)
  }
}
