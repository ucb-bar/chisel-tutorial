// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class SimpleALUTests(c: SimpleALU) extends PeekPokeTester(c) {
  for (n <- 0 until 64) {
    val a      = rnd.nextInt(16)
    val b      = rnd.nextInt(16)
    val opcode = rnd.nextInt(4)
  // for (a <- 0 until 16) {
    // for (b <- 0 until 16) {
      // for (opcode <- 0 until 4) {
        var output = 0
        if (opcode == 0) {
          output = ((a+b) & 0xF)
        } else if (opcode == 1) {
          output = ((a-b) & 0xF)
        } else if (opcode == 2) {
          output = a
        } else {
          output = b
        }
        poke(c.io.a, a)
        poke(c.io.b, b)
        poke(c.io.opcode, opcode)
        step(1)
        expect(c.io.out, output)
  }
      // }}}
}

class SimpleALUTester extends ChiselFlatSpec {
  behavior of "SimpleALU"
  backends foreach {backend =>
    it should s"perform correct math operation on dynamic operand in $backend" in {
      Driver(() => new SimpleALU, backend)((c) => new SimpleALUTests(c)) should be (true)
    }
  }
}

