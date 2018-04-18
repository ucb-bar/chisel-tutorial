// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class DarkenTests(c: Darken, infile: java.io.InputStream, outfilename: String) extends PeekPokeTester(c) {
  val inPic  = Image(infile)
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
      Driver(() => new Darken(), backend)(
        c => new DarkenTests(c, getClass.getResourceAsStream("/in.im24"), "out.im24")) should be (true)
    }
  }
}
