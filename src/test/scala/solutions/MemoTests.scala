// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.PeekPokeTester

class MemoTests(c: Memo) extends PeekPokeTester(c) {
  def rd(addr: Int, data: Int) = {
    poke(c.io.ren, 1)
    poke(c.io.rdAddr, addr)
    step(1)
    expect(c.io.rdData, data)
  }
  def wr(addr: Int, data: Int)  = {
    poke(c.io.wen,    1)
    poke(c.io.wrAddr, addr)
    poke(c.io.wrData, data)
    step(1)
  }
  wr(0, 1)
  rd(0, 1)
  wr(9, 11)
  rd(9, 11)
}
