package TutorialExamples

import Chisel._
import Node._
import scala.collection.mutable.HashMap
import util.Random

class BasicALU extends Module {
  val io = new Bundle {
    val a = UInt(INPUT, 4)
    val b = UInt(INPUT, 4)
    val opcode = UInt(INPUT, 4)
    val output = UInt(OUTPUT, 4)
  }
  io.output := UInt(0) //THIS SEEMS LIKE A HACK/BUG
  when (io.opcode === UInt(0)) {
    io.output := io.a //pass A
  } .elsewhen (io.opcode === UInt(1)) {
    io.output := io.b //pass B
  } .elsewhen (io.opcode === UInt(2)) {
    io.output := io.a + UInt(1) //increment A by 1
  } .elsewhen (io.opcode === UInt(3)) {
    io.output := io.a - UInt(1) //increment B by 1
  } .elsewhen (io.opcode === UInt(4)) {
    io.output := io.a + UInt(4) //increment A by 4
  } .elsewhen (io.opcode === UInt(5)) {
    io.output := io.a - UInt(4) //decrement A by 4
  } .elsewhen (io.opcode === UInt(6)) {
    io.output := io.a + io.b //add A and B
  } .elsewhen (io.opcode === UInt(7)) {
    io.output := io.a - io.b //subtract B from A
  } .elsewhen (io.opcode === UInt(8)) {
    io.output := io.a < io.b //set on A less than B
  } .otherwise { 
    io.output :=  (io.a === io.b).toUInt() //set on A equal to B
  }
}

class SimpleALU extends Module {
  val io = new Bundle {
    val a = UInt(INPUT, 4)
    val b = UInt(INPUT, 4)
    val opcode = UInt(INPUT, 2)
    val output = UInt(OUTPUT, 4)
  }
  io.output := UInt(0) 
  when (io.opcode === UInt(0)) {
    io.output := io.a + io.b   //ADD
  } .elsewhen (io.opcode === UInt(1)) {
    io.output := io.b - io.b   //SUB
  } .elsewhen (io.opcode === UInt(2)) {
    io.output := io.a  	       //PASS A
  } .otherwise {
    io.output := io.b          //PASS B
  }
}

class BasicALUTests(c: BasicALU) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars = new HashMap[Node, Node]()
    var a_in = 1
    var b_in = 2
    var output = 0
    var opcode = 0
    for (a <- 0 until 16) {
      for (b <- 0 until 16) {
	for (s <- 0 until 4) {
	  a_in   = a
	  b_in   = b
	  opcode = s
	  if (opcode == 0) {
	    output = ((a+b) & 0xF)
	  } else if (opcode == 1) {
	    output = ((a-b) & 0xF)
	  } else if (opcode == 2) {
	    output = a
	  } else {
	    output = b
 	  }
          vars(c.io.a) = UInt(a_in)
	  vars(c.io.b) = UInt(b_in)
	  vars(c.io.opcode) = UInt(opcode)
	  vars(c.io.output) = UInt(output)
	}
      }
    }
    allGood
  }
}
