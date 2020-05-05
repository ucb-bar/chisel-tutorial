// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class GCDTests(c: GCD) extends PeekPokeTester(c) {
  val inputs = List( (48, 32), (7, 3), (100, 10) )
  val outputs = List( 16, 1, 10)

  var i = 0
  do {
    poke(c.io.a, inputs(i)._1)
    poke(c.io.b, inputs(i)._2)

    poke(c.io.load, 1)
    step(1)
    poke(c.io.load, 0)

    var ready = false

    do {
      ready = peek(c.io.valid) == 1
      step(1)
    } while (t < 100 && ! ready)

    expect(c.io.out, outputs(i))
    i += 1
  } while (t < 100 && i < 3)

  if (t >= 100) fail
}

class GCDTester extends ChiselFlatSpec {
  behavior of "GCD"

  backends foreach {backend =>
    it should s"test the basic gcd circuit" in {
      Driver(() => new GCD, backend)((c) => new GCDTests(c)) should be (true)
    }
  }
}


