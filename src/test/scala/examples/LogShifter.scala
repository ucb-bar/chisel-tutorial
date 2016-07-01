// See LICENSE.txt for license details.
package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }

class LogShifterTests(c: LogShifter, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
}

