// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._

class FullAdder2 extends Module {
  val io = new Bundle {
    val a    = UInt(INPUT, 2)
    val b    = UInt(INPUT, 2)
    val cin  = UInt(INPUT, 2)
    val sum  = UInt(OUTPUT, 2)
    val cout = UInt(OUTPUT, 2)
  }

  //Generate the sum
  val a_xor_b = io.a ^ io.b
  io.sum := a_xor_b ^ io.cin
  //Generate the carry
  val a_and_b = io.a & io.b
  val b_and_cin = io.b & io.cin
  val a_and_cin = io.a & io.cin
  io.cout := a_and_b | b_and_cin | a_and_cin
}

class FullAdder2Tests(c: FullAdder2) extends Tester(c) {  
}
