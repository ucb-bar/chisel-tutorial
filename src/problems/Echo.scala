package TutorialProblems

import Chisel._
import Node._
import java.io.File
import scala.collection.mutable.HashMap

class Echo extends Module {
  val io = new Bundle {
    val in  = Bits(INPUT,  8)
    val out = Bits(OUTPUT, 8)
  }

  val samples = 4096
  require(isPow2(samples))

  val inSigned = (io.in - UInt(128)).toSInt // convert to two's complement

  val history = Mem(samples, SInt(width = 8))
  val pos = RegReset(UInt(0, log2Up(samples)))
  pos := pos + UInt(1)

  // y[n] = x[n] + 0.5 * y[n - samples]
  val out = inSigned + (history(pos) >> UInt(1))

  history(pos) := out

  val outUnsigned = out + UInt(128) // convert back to excess-128 format
  io.out := outUnsigned
}

class EchoTests(c: Echo, val infilename: String, val outfilename: String) extends Tester(c, Array(c.io)) {  
  defTests {
    val svars = new HashMap[Node, Node]()
    val ovars = new HashMap[Node, Node]()

    val in  = WavIn(infilename)
    val out = WavOut(outfilename, in.getFormat)

    var sample = in.read
    while (sample != -1) {
      svars(c.io.in) = SInt(sample)
      step(svars, ovars, isTrace = false)
      out += ovars(c.io.out).litValue().toByte
      sample = in.read
    }

    out.flush
    out.close
    true
  }
}
