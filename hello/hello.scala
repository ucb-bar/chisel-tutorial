package Hello

import Chisel._
import scala.collection.mutable.HashMap

class Hello extends Module {
  val io = new Bundle { 
    val out = UInt(OUTPUT, 8)
  }
  io.out := UInt(42)
}

class HelloTests(c: Hello) extends Testy(c) {
  step(1)
  expect(c.io.out, 42)
}

object Hello {
  def main(args: Array[String]): Unit = {
    // Uncomment to ignore command-line args and always build & run C emulator
    // val args = Array("--backend", "c", "--genHarness", "--compile", "--test")
    val cutArgs = args.slice(1, args.length)
    chiselMainTest(cutArgs, () => Module(new Hello())) {
      c => new HelloTests(c) }
  }
}
