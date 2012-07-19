package Tutorial

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import Literal._
import scala.util.Random

class LinkIO extends Bundle { 
  val data  = Bits(OUTPUT, 16) 
  val valid = Bool(OUTPUT)
}

class FilterIO extends Bundle { 
  val in  = new LinkIO().flip
  val out = new LinkIO()
}

class Filter extends Component { 
  val io  = new FilterIO()

  // -------------------------------- \\
  // Filter out even numbers
  // Should only need two lines of code
  // -------------------------------- \\


  // -------------------------------- \\
}

class FilterTests(c: Filter) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val maxInt  = 1 << 16
    for (i <- 0 until 10) {
      vars.clear()
      val in                = rnd.nextInt(maxInt)
      vars(c.io.in.valid)   = Bool(true)
      vars(c.io.in.data)    = UFix(in)
      val isOdd             = (in&1)!=0
      vars(c.io.out.valid)  = Bool(isOdd)
      if (isOdd)
        vars(c.io.out.data) = UFix(in)
      allGood               = step(vars) && allGood
    }
    allGood
  }  
}

