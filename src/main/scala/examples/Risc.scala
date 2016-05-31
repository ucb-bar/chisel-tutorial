package examples

import Chisel._
import Chisel.iotesters._

class Risc extends Module {
  val io = new Bundle {
    val isWr   = Bool(INPUT)
    val wrAddr = UInt(INPUT, 8)
    val wrData = Bits(INPUT, 32)
    val boot   = Bool(INPUT)
    val valid  = Bool(OUTPUT)
    val out    = Bits(OUTPUT, 32)
  }
  val file = Mem(256, Bits(width = 32))
  val code = Mem(256, Bits(width = 32))
  val pc   = Reg(init=UInt(0, 8))
  
  val add_op :: imm_op :: Nil = Enum(Bits(), 2)

  val inst = code(pc)
  val op   = inst(31,24)
  val rci  = inst(23,16)
  val rai  = inst(15, 8)
  val rbi  = inst( 7, 0)

  val ra = Mux(rai === Bits(0), Bits(0), file(rai))
  val rb = Mux(rbi === Bits(0), Bits(0), file(rbi))
  val rc = Wire(Bits(width = 32))

  io.valid := Bool(false)
  io.out   := Bits(0)
  rc       := Bits(0)

  when (io.isWr) {
    code(io.wrAddr) := io.wrData
  } .elsewhen (io.boot) {
    pc := UInt(0)
  } .otherwise {
    switch(op) {
      is(add_op) { rc := ra + rb }
      is(imm_op) { rc := (rai << UInt(8)) | rbi }
    }
    io.out := rc
    when (rci === UInt(255)) {
      io.valid := Bool(true)
    } .otherwise {
      file(rci) := rc
    }
    pc := pc + UInt(1)
  }
}

object Opcodes {
  val add_op = 0
  val imm_op = 1
}
