// See LICENSE.txt for license details.
package problems

import Chisel.iotesters.{ Backend => TesterBackend, _ }

class RealGCDTests(c: RealGCD, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  val inputs = List( (48, 32), (7, 3), (100, 10) )
  val outputs = List( 16, 1, 10)

  var i = 0
  do {
    var transfer = false
    do {
      poke(c.io.in.bits.a, inputs(i)._1)
      poke(c.io.in.bits.b, inputs(i)._2)
      poke(c.io.in.valid,  1)
      transfer = (peek(c.io.in.ready) == 1)
      step(1)
    } while (t < 100 && !transfer)

    do {
      poke(c.io.in.valid, 0)
      step(1)
    } while (t < 100 && (peek(c.io.out.valid) == 0))

    expect(c.io.out.bits, outputs(i))
    i += 1;
  } while (t < 100 && i < 3)
  if (t >= 100) fail
}
