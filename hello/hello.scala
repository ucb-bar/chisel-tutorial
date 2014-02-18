package Hello

import Chisel._
import scala.collection.mutable.HashMap

class Hello extends Module {
  val io = new Bundle { 
    val out = UInt(OUTPUT, 8)
  }
  io.out := UInt(42)
}

class HelloTests(c: Hello) extends Tester(c, Array(c.io)) {
  defTests {
    val vars = new HashMap[Node, Node]()
    vars(c.io.out) = UInt(42)
    step(vars)
  }
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
