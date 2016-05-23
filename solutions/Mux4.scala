package TutorialSolutions

import Chisel._
import Chisel.iotesters._

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

  val m1 = Module(new Mux2())
  m1.io.sel := io.sel(0)
  m1.io.in0 := io.in2
  m1.io.in1 := io.in3

  val m2 = Module(new Mux2())
  m2.io.sel := io.sel(1)
  m2.io.in0 := m0.io.out
  m2.io.in1 := m1.io.out

  io.out := m2.io.out
}

class Mux4Tests(c: Mux4, b: Option[Backend] = None) extends ClassicTester(c, _backend=b) {
  for (s0 <- 0 until 2) {
    for (s1 <- 0 until 2) {
      for(i0 <- 0 until 2) {
        for(i1 <- 0 until 2) {
          for(i2 <- 0 until 2) {
            for(i3 <- 0 until 2) {
              poke(c.io.sel, s1 << 1 | s0)
              poke(c.io.in0, i0)
              poke(c.io.in1, i1)
              poke(c.io.in2, i2)
              poke(c.io.in3, i3)
              step(1)
              val out = if(s1 == 1) {
                          if (s0 == 1) i3 else i2
                        } else {
                          if (s0 == 1) i1 else i0 
                        }
              expect(c.io.out, out)
            }
          }
        }
      } 
    }
  }
}
