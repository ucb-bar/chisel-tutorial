package Hello

import Chisel._
import scala.collection.mutable.HashMap

class Hello extends Module {
  val io = new Bundle { 
    val out = UInt(OUTPUT, 8)
  }
  io.out := UInt(33)
}

class HelloTests(c: Hello) extends Tester(c, Array(c.io)) {
  defTests {
    val vars = new HashMap[Node, Node]()
    vars(c.io.out) = UInt(33)
    step(vars)
  }
}

object Hello {
  def main(args: Array[String]): Unit = {
    val args = Array("--backend", "c", "--genHarness", "--compile", "--test")
    chiselMainTest(args, () => Module(new Hello())) {
      c => new HelloTests(c) }
  }
}
