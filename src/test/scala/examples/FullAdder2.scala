package examples

import Chisel._
import Chisel.iotesters._

class FullAdder2Tests(c: FullAdder2, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
}
