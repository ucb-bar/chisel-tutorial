// See LICENSE.txt for license details.
package problems

import Chisel._

class Mux4 extends Module {
  val io = new Bundle {
    val in0 = Bits(INPUT,  1)
    val in1 = Bits(INPUT,  1)
    val in2 = Bits(INPUT,  1)
    val in3 = Bits(INPUT,  1)
    val sel = Bits(INPUT,  2)
    val out = Bits(OUTPUT, 1)
  }

  //-------------------------------------------------------------------------\\

  // Modify this section to build a 4-to-1 mux out of 3 2-to-1 mux
  // The first mux is already done for you


  //-------------------------------------------------------------------------\\

  val m0 = Module(new Mux2())
  m0.io.sel := io.sel(0)
  m0.io.in0 := io.in0
  m0.io.in1 := io.in1
}
