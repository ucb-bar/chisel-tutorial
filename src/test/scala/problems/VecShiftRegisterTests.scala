// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

class VecShiftRegisterTests(c: VecShiftRegister) extends PeekPokeTester(c) {
  val reg     = Array.fill(4){ 0 }
  val ins     = Array.fill(4){ 0 }
  // Initialize the delays.
  for (i <- 0 until 4)
    poke(c.io.ins(i), 0)
  poke(c.io.load, 1)
  step(1)

  for (t <- 0 until 16) {
    for (i <- 0 until 4)
      ins(i) = rnd.nextInt(16)
    val shift = rnd.nextInt(2)
    val load  = rnd.nextInt(2)
    for (i <- 0 until 4)
      poke(c.io.ins(i), ins(i))
    poke(c.io.load,  load)
    poke(c.io.shift, shift)
    step(1)
    if (load == 1) {
      for (i <- 0 until 4) 
        reg(i) = ins(i)
    } else if (shift == 1) {
      for (i <- 3 to 1 by -1)
        reg(i) = reg(i-1)
      reg(0) = ins(0)
    }
    expect(c.io.out, reg(3))
  }
}
