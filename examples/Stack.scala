package TutorialExamples

import Chisel._
import scala.collection.mutable.HashMap
import scala.util.Random

class Stack(depth:Int) extends Module {
  val io = new Bundle {
    val dataIn  = UInt(INPUT, 32)
    val dataOut = UInt(OUTPUT, 32)
    val push    = Bool(INPUT)
    val pop     = Bool(INPUT)
    val en      = Bool(INPUT)
  }

  val stack_mem = Mem(UInt(width = 32), depth, seqRead = false)
  val sp = Reg(init = UInt(0, width = log2Up(depth)))
  val dataOut = Reg(init = UInt(0, width = 32))

  when(io.en && io.push && (sp != UInt(depth-1))) {
    stack_mem(sp + UInt(1)) := io.dataIn
    sp := sp + UInt(1)
  } .elsewhen(io.en && io.pop && (sp > UInt(0))) {
    sp := sp - UInt(1)
  }
  
  when(io.en) {
    dataOut := stack_mem(sp)
  }

  io.dataOut := dataOut
}

class StackTests(c: Stack) extends Tester(c, Array(c.io)) {  
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()

    /*    
    var enable = Bool(true)
    var push = Bool(true)
    var pop = Bool(true)
    var dataIn = UInt(width = 32)
    var dataOut = UInt(width = 32)
    
    pop = Bool(false); dataIn = UInt(1)
    dataOut = UInt(0)
    
    vars(c.io.pop) = pop
    vars(c.io.push) = push
    vars(c.io.en) = enable
    vars(c.io.dataIn) = dataIn
    vars(c.io.dataOut) = dataOut
    */
        
    allGood
//    allGood        = step(vars) && allGood
    }
    
}


