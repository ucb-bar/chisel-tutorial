package Tutorial

import Chisel._
import scala.collection.mutable.HashMap

class GCD extends Component {
  val io = new Bundle {
    val a  = UFix(INPUT,  16)
    val b  = UFix(INPUT,  16)
    val e  = Bool(INPUT)
    val z  = UFix(OUTPUT, 16)
    val v  = Bool(OUTPUT)
  }
  val x  = Reg(){ UFix() }
  val y  = Reg(){ UFix() }
  when   (x > y) { x := x - y } 
  unless (x > y) { y := y - x }
  when (io.e) { x := io.a; y := io.b }
  io.z := x
  io.v := y === UFix(0)
}

class GCDTests(c: GCD) extends Tester(c, Array(c.io)) {
  defTests {
    val (a, b, z) = (64, 48, 16)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()
    var t = 0
    do {
      val first = (t == 0)
      svars.clear()
      svars(c.io.a) = UFix(a)
      svars(c.io.b) = UFix(b)
      svars(c.io.e) = Bool(first)
      step(svars, ovars)
      t += 1
    } while (t <= 1 || ovars(c.io.v).litValue() == 0)
    ovars(c.io.z).litValue() == z
  }
}
