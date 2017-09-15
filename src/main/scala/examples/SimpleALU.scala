// See LICENSE.txt for license details.
package examples

import chisel3._

class BasicALU extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(4.W))
    val b = Input(UInt(4.W))
    val opcode = Input(UInt(4.W))
    val out = Output(UInt(4.W))
  })
  io.out := 0.U //THIS SEEMS LIKE A HACK/BUG
  when (io.opcode === 0.U) {
    io.out := io.a //pass A
  } .elsewhen (io.opcode === 1.U) {
    io.out := io.b //pass B
  } .elsewhen (io.opcode === 2.U) {
    io.out := io.a + 1.U //increment A by 1
  } .elsewhen (io.opcode === 3.U) {
    io.out := io.a - 1.U //increment B by 1
  } .elsewhen (io.opcode === 4.U) {
    io.out := io.a + 4.U //increment A by 4
  } .elsewhen (io.opcode === 5.U) {
    io.out := io.a - 4.U //decrement A by 4
  } .elsewhen (io.opcode === 6.U) {
    io.out := io.a + io.b //add A and B
  } .elsewhen (io.opcode === 7.U) {
    io.out := io.a - io.b //subtract B from A
  } .elsewhen (io.opcode === 8.U) {
    io.out := io.a < io.b //set on A less than B
  } .otherwise { 
    io.out :=  (io.a === io.b).asUInt //set on A equal to B
  }
}

class SimpleALU extends Module {
  val io = IO(new Bundle {
    val a      = Input(UInt(4.W))
    val b      = Input(UInt(4.W))
    val opcode = Input(UInt(2.W))
    val out = Output(UInt(4.W))
  })
  io.out := 0.U
  when (io.opcode === 0.U) {
    io.out := io.a + io.b //ADD
  } .elsewhen (io.opcode === 1.U) {
    io.out := io.a - io.b //SUB
  } .elsewhen (io.opcode === 2.U) {
    io.out := io.a  	     //PASS A
  } .otherwise {
    io.out := io.b        //PASS B
  }
}
