package Tutorial

import Chisel._
import scala.collection.mutable.HashMap

class GCD extends Component {
  val io = new Bundle {
    val a = UFix(16, INPUT)
    val b = UFix(16, INPUT)
    val z = UFix(16, OUTPUT)
    val v = Bool(OUTPUT)
  }
  val x  = Reg(resetVal = io.a)
  val y  = Reg(resetVal = io.b)
  when   (x > y) { x := x - y } 
  unless (x > y) { y := y - x }
  io.z := x
  io.v := y === UFix(0)
}

class GCDTests(c: GCD) extends Tester(c, Array(c.io)) {
  defTests {
    val (a, b, z) = (64, 48, 16)
    val vars = new HashMap[Node, Node]()
    var t = 0
    do {
      vars.clear()
      vars(c.io.a) = UFix(a)
      vars(c.io.b) = UFix(b)
      step(vars)
      t += 1
    } while (t <= 1 || vars(c.io.v).litValue() == 0)
    vars(c.io.z).litValue() == z
  }
}
