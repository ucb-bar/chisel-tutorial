package hello

import Chisel._
import Chisel.iotesters._
import java.io._

class Hello extends Module {
  val io = new Bundle {
    val out = UInt(OUTPUT, 8)
  }
  io.out := UInt(42)
  printf("Hello, world\n")
}

class HelloTests(c: Hello, emulBinPath: Option[String] = None) extends ClassicTester(c, emulBinPath = emulBinPath) {
  step(1)
  expect(c.io.out, 42)
}
