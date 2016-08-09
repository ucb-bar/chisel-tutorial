// See LICENSE.txt for license details.
package hello

import Chisel._
import Chisel.iotesters.{PeekPokeTester, Driver}

class Hello extends Module {
  val io = new Bundle { 
    val out = UInt(OUTPUT, 8)
  }
  io.out := UInt(42)
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
