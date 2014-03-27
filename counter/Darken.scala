package TutorialExamples

import Chisel._

class Darken extends Module {
  val io = new Bundle {
    val in  = Bits(INPUT, 8)
    val out = Bits(OUTPUT, 8)
  }

  val res = io.in << UInt(1)
  io.out := res
  counter(res)
}

class DarkenTests(c: Darken, val infilename: String, val outfilename: String) extends CounterTester(c, false) {  
  val inPic  = Image(infilename)
  val outPic = Image(inPic.w, inPic.h, inPic.d)
  step(1)
  for (i <- 0 until inPic.data.length) {
    val rin = inPic.data(i)
    val  in = if (rin < 0) 256 + rin else rin
    poke(c.io.in, in)
    step(1)
    val out = peek(c.io.out)
    outPic.data(i) = out.toByte
  }
  outPic.write(outfilename)
  ok = true
}
