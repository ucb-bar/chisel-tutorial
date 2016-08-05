// See LICENSE.txt for license details.
package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }

class DarkenTests(c: Darken, val infilename: String, val outfilename: String, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
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
}

class DarkenTester extends ChiselFlatSpec {
  behavior of "Darken"
  backends foreach {backend =>
    it should s"darken an image in $backend" in {
      runPeekPokeTester(() => new Darken(), backend){
        (c,b) => new DarkenTests(c, "src/test/resources/in.im24", "out.im24", b)
      } should be (true)
    }
  }
}
