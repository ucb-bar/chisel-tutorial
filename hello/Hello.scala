package Hello

import Chisel._
import scala.collection.mutable.HashMap

class Hello extends Module {
  val io = new Bundle { 
    val out = UInt(OUTPUT, 8)
  }
  io.out := UInt(42)
}

class HelloTests(c: Hello) extends Tester(c) {
  step(1)
  expect(c.io.out, 42)
}

object Hello {
  def main(args: Array[String]): Unit = {
    val margs = Array("--backend", "c", "--genHarness", "--compile", "--test")
    chiselMainTest(margs, () => Module(new Hello())) {
      c => new HelloTests(c) }
    // Uncomment to allow command-line args
    // val margs = args.slice(1, args.length)
    // chiselMain(margs, () => Module(new Hello())) 
  }
}
