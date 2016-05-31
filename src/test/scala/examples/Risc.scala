package examples

import Chisel._
import Chisel.iotesters._

class RiscTests(c: Risc, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  def wr(addr: BigInt, data: BigInt)  = {
    poke(c.io.isWr,   1)
    poke(c.io.wrAddr, addr)
    poke(c.io.wrData, data)
    step(1)
  }
  def boot()  = {
    poke(c.io.isWr, 0)
    poke(c.io.boot, 1)
    step(1)
  }
  def tick()  = {
    poke(c.io.isWr, 0)
    poke(c.io.boot, 0)
    step(1)
  }
  /*
  def I (op: UInt, rc: Int, ra: Int, rb: Int) = 
    Cat(op, UInt(rc, 8), UInt(ra, 8), UInt(rb, 8))
*/

  def I (op: Int, rc: Int, ra: Int, rb: Int) = 
    ((op & 1) << 24) | ((rc & Integer.parseInt("FF", 16)) << 16) | ((ra & Integer.parseInt("FF", 16)) << 8) | (rb & Integer.parseInt("FF", 16))
/*
  val app  = Array(I(c.imm_op,   1, 0, 1), // r1 <- 1
                   I(c.add_op,   1, 1, 1), // r1 <- r1 + r1
                   I(c.add_op,   1, 1, 1), // r1 <- r1 + r1
                   I(c.add_op, 255, 1, 0)) // rh <- r1
*/

  val app  = Array(I(Opcodes.imm_op,   1, 0, 1), // r1 <- 1
                   I(Opcodes.add_op,   1, 1, 1), // r1 <- r1 + r1
                   I(Opcodes.add_op,   1, 1, 1), // r1 <- r1 + r1
                   I(Opcodes.add_op, 255, 1, 0)) // rh <- r1

  wr(0, 0) // skip reset
  for (addr <- 0 until app.length) 
    wr(addr, app(addr))
  boot()
  var k = 0
  do {
    tick(); k += 1
  } while (peek(c.io.valid) == 0 && k < 10)
  assert(k < 10, "TIME LIMIT")
  expect(c.io.out, 4)
}
