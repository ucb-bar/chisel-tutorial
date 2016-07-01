// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._

class ReadCmd extends Bundle {
  val addr = UInt(width = 32);
}

class WriteCmd extends ReadCmd {
  val data = UInt(width = 32)
}

class Packet extends Bundle {
  val header = UInt(width = 8)
  val body   = Bits(width = 64)
}

class RouterIO(n: Int) extends Bundle {
  override def cloneType = new RouterIO(n).asInstanceOf[this.type]
  val reads   = new DeqIO(new ReadCmd())
  val replies = new EnqIO(UInt(width = 8))
  val writes  = new DeqIO(new WriteCmd())
  val in      = new DeqIO(new Packet())
  val outs    = Vec(n, new EnqIO(new Packet()))
}

class Router extends Module {
  val depth = 32
  val n     = 4
  val io    = new RouterIO(n)
  val tbl   = Mem(depth, UInt(width = BigInt(n).bitLength))
  when(io.reads.valid && io.replies.ready) { 
    val cmd = io.reads.deq();  io.replies.enq(tbl(cmd.addr))  
  } .elsewhen(io.writes.valid) { 
    val cmd = io.writes.deq(); tbl(cmd.addr) := cmd.data
  } .elsewhen(io.in.valid) {
    val pkt = io.in.bits
    val idx = tbl(pkt.header(0))
    when (io.outs(idx).ready) {
      io.in.deq(); io.outs(idx).enq(pkt)
    }
  } 
}

class RouterTests(c: Router) extends Tester(c) {  
  def rd(addr: Int, data: Int) = {
    poke(c.io.in.valid,        0)
    poke(c.io.writes.valid,    0)
    poke(c.io.reads.valid,     1)
    poke(c.io.replies.ready,   1)
    poke(c.io.reads.bits.addr, addr)
    step(1)
    expect(c.io.replies.bits, data)
  }
  def wr(addr: Int, data: Int)  = {
    poke(c.io.in.valid,         0)
    poke(c.io.reads.valid,      0)
    poke(c.io.writes.valid,     1)
    poke(c.io.writes.bits.addr, addr)
    poke(c.io.writes.bits.data, data)
    step(1)
  }
  def isAnyValidOuts(): Boolean = {
    for (out <- c.io.outs)
      if (peek(out.valid) == 1)
        return true
    false
  }
  def rt(header: Int, body: Int)  = {
    for (out <- c.io.outs)
      poke(out.ready, 1)
    poke(c.io.reads.valid,    0)
    poke(c.io.writes.valid,   0)
    poke(c.io.in.valid,       1)
    poke(c.io.in.bits.header, header)
    poke(c.io.in.bits.body,   body)
    var i = 0
    do {
      step(1)
      i += 1
    } while (!isAnyValidOuts() || i > 10)
    expect(i < 10, "FIND VALID OUT")
  }
  rd(0, 0)
  wr(0, 1)
  rd(0, 1)
  rt(0, 1)
}
