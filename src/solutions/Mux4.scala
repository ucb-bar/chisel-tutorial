package TutorialSolutions

import Chisel._
import scala.math._
import scala.collection.mutable.HashMap

class Mux4 extends Component {
  val io = new Bundle {
    val in0 = Bits(INPUT,  1)
    val in1 = Bits(INPUT,  1)
    val in2 = Bits(INPUT,  1)
    val in3 = Bits(INPUT,  1)
    val sel = Bits(INPUT,  2)
    val out = Bits(OUTPUT, 1)
  }

  val m0 = new Mux2()
  m0.io.sel := io.sel(0) 
  m0.io.in0 := io.in0
  m0.io.in1 := io.in1

  val m1 = new Mux2()
  m1.io.sel := io.sel(0)
  m1.io.in0 := io.in2
  m1.io.in1 := io.in3

  val m2 = new Mux2()
  m2.io.sel := io.sel(1)
  m2.io.in0 := m0.io.out
  m2.io.in1 := m1.io.out

  io.out := m2.io.out
}

class Mux4Tests(c: Mux4) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars = new HashMap[Node, Node]()
    
    for (s0 <- 0 until 2) {
      for (s1 <- 0 until 2) {
        for(i0 <- 0 until 2) {
          for(i1 <- 0 until 2) {
            for(i2 <- 0 until 2) {
              for(i3 <- 0 until 2) {
                vars.clear()
                vars(c.io.sel) = Bits(s1 << 1 | s0)
                vars(c.io.in0) = Bits(i0)
                vars(c.io.in1) = Bits(i1)
                vars(c.io.in2) = Bits(i2)
                vars(c.io.in3) = Bits(i3)
                
                vars(c.io.out) = 
                  if(s1 == 1) { 
                    if (s0 == 1) Bits(i3) else Bits(i2) 
                  } else { 
                    if (s0 == 1) Bits(i1) else Bits(i0) 
                  }
                allGood = step(vars) && allGood
              }
            }
          }
        } 
      }
    }
    allGood
  }
}
