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

  val x = Reg(){ Bits() }
  val y = Reg(){ Bits() }
  val first = Reg(resetVal = Bool(true) )

  io.in.ready := first || y === Bits(0)

  when (io.in.valid && io.in.ready) {
    x := io.in.bits.a
    y := io.in.bits.b
    first := Bool(false)
  }

  when (!first && y != Bits(0)) {
    when (x > y) { x := y; y := x}
    when (x <= y) { y := y - x }
  }

  io.out.bits := x
  io.out.valid := y === Bits(0) && !first
}

