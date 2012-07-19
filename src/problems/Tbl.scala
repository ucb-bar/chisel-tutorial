package Tutorial

import Chisel._
import scala.collection.mutable.HashMap
import util.Random

class Tbl extends Component {
  val io = new Bundle {
    val addr = UFix(INPUT,  8)
    val out  = UFix(OUTPUT, 8)
  }
  val r = Vec(Range(0, 256).map(UFix(_))){ UFix(width = 8) }
  io.out := r(io.addr)
}

class TblTests(c: Tbl) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    for (t <- 0 until 16) {
      vars.clear()
      val addr        = rnd.nextInt(256)
      vars(c.io.addr) = UFix(addr)
      vars(c.io.out)  = UFix(addr)
      allGood         = step(vars) && allGood
    }
    allGood
  }
}

