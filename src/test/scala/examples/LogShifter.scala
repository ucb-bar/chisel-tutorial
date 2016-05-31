package examples


import Chisel.iotesters._

class LogShifterTests(c: LogShifter, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
}

