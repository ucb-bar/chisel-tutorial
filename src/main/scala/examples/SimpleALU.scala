package examples

import Chisel._
import Chisel.iotesters._

class BasicALU extends Module {
  val io = new Bundle {
    val a = UInt(INPUT, 4)
    val b = UInt(INPUT, 4)
    val opcode = UInt(INPUT, 4)
    val out = UInt(OUTPUT, 4)
  }
  io.out := UInt(0) //THIS SEEMS LIKE A HACK/BUG
  when (io.opcode === UInt(0)) {
    io.out := io.a //pass A
  } .elsewhen (io.opcode === UInt(1)) {
    io.out := io.b //pass B
  } .elsewhen (io.opcode === UInt(2)) {
    io.out := io.a + UInt(1) //increment A by 1
  } .elsewhen (io.opcode === UInt(3)) {
    io.out := io.a - UInt(1) //increment B by 1
  } .elsewhen (io.opcode === UInt(4)) {
    io.out := io.a + UInt(4) //increment A by 4
  } .elsewhen (io.opcode === UInt(5)) {
    io.out := io.a - UInt(4) //decrement A by 4
  } .elsewhen (io.opcode === UInt(6)) {
    io.out := io.a + io.b //add A and B
  } .elsewhen (io.opcode === UInt(7)) {
    io.out := io.a - io.b //subtract B from A
  } .elsewhen (io.opcode === UInt(8)) {
    io.out := io.a < io.b //set on A less than B
  } .otherwise { 
    io.out :=  (io.a === io.b).toUInt() //set on A equal to B
  }
}

class SimpleALU extends Module {
  val io = new Bundle {
    val a      = UInt(INPUT,  4)
    val b      = UInt(INPUT,  4)
    val opcode = UInt(INPUT,  2)
    val out = UInt(OUTPUT, 4)
  }
  io.out := UInt(0) 
  when (io.opcode === UInt(0)) {
    io.out := io.a + io.b //ADD
  } .elsewhen (io.opcode === UInt(1)) {
    io.out := io.a - io.b //SUB
  } .elsewhen (io.opcode === UInt(2)) {
    io.out := io.a  	     //PASS A
  } .otherwise {
    io.out := io.b        //PASS B
  }
}
