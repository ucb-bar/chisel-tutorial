package Tutorial

import Chisel._
import Node._
import scala.collection.mutable.HashMap

class RealGCDInput extends Bundle {
  val a = Bits(width = 16)
  val b = Bits(width = 16)
}

class RealGCD extends Component {
  val io  = new Bundle {
    val in  = new FIFOIO()( new RealGCDInput() ).flip()
    val out = new PipeIO()( Bits(width = 16) )
  }

  // flush this out ...

}

class RealGCDTests(c: RealGCD) extends Tester(c, Array(c.io)) {
  defTests {
    val inputs = List( (48, 32), (7, 3), (100, 10) )
    val outputs = List( 16, 1, 10)
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    // let it spin for a bit
    for (i <- 0 until 5) {
      svars(c.io.in.valid) = Bool(false)
      step(svars, ovars, false)
    }

    var i = 0
    var j = 0
    var t = 0

    var allPassed = true

    while(t < 20 && (i < 3 || j < 3)) {
      if (i < 3) {
        svars(c.io.in.bits.a) = Bits(inputs(i)._1)
        svars(c.io.in.bits.b) = Bits(inputs(i)._2)
        svars(c.io.in.valid) = Bool(true)
      } else {
        svars(c.io.in.valid) = Bool(false)
      }

      // this advances the clock
      step(svars, ovars)

      // bump counters and check outputs after advancing clock
      if (ovars(c.io.in.ready).litValue() == 1) i += 1
      if (ovars(c.io.out.valid).litValue() == 1) {
        allPassed = allPassed && ovars(c.io.out.bits).litValue() == outputs(j)
        j += 1
      }
      t += 1
    }
    allPassed && t < 20
  }
}
