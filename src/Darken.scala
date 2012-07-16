package Tutorial

import Chisel._
import Node._
import scala.collection.mutable.HashMap

class Darken extends Component {
  val io = new Bundle {
    val in  = Bits(8, INPUT)
    val out = Bits(8, OUTPUT)
  }

  io.out := io.in << UFix(1)
}

class DarkenTests(c: Darken, val infilename: String, val outfilename: String) extends Tester(c, Array(c.io)) {  
  defTests {
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    val inPic  = Image(infilename)
    val outPic = Image(inPic.w, inPic.h, inPic.d)
    step(svars, ovars, false)
    for (i <- 0 until inPic.data.length) {
      val rin = inPic.data(i)
      val  in = if (rin < 0) 256 + rin else rin
      svars(c.io.in) = Bits(in)
      step(svars, ovars, false)
      val out = ovars(c.io.out).litValue()
      outPic.data(i) = out.toByte
    }
    outPic.write(outfilename)
    true
  }
}
