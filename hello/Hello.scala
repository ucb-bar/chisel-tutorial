package Hello

import Chisel._
import Chisel.iotesters._

class Hello extends Module {
  val io = new Bundle { 
    val out = UInt(OUTPUT, 8)
  }
  io.out := UInt(42)
}

class HelloTests(c: Hello) extends ClassicTester(c) {
  step(1)
  expect(c.io.out, 42)
}

object Hello {
  def main(args: Array[String]): Unit = {
    val tutArgs = args.slice(1, args.length)
    chiselMainTest(tutArgs, () => new Hello()) {
      c => new HelloTests(c) }
  }
}
