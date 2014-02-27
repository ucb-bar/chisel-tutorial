package TutorialExamples

import Chisel._
import scala.collection.mutable.HashMap
import scala.collection.mutable.{Stack => ScalaStack}
import scala.util.Random

class Stack(val depth: Int) extends Module {
  val io = new Bundle {
    val push    = Bool(INPUT)
    val pop     = Bool(INPUT)
    val en      = Bool(INPUT)
    val dataIn  = UInt(INPUT,  32)
    val dataOut = UInt(OUTPUT, 32)
  }

  val stack_mem = Mem(UInt(width = 32), depth, seqRead = false)
  val sp        = Reg(init = UInt(0, width = log2Up(depth+1)))
  val dataOut   = Reg(init = UInt(0, width = 32))

  when (io.en) {
    when(io.push && (sp < UInt(depth))) {
      stack_mem(sp) := io.dataIn
      sp := sp + UInt(1)
    } .elsewhen(io.pop && (sp > UInt(0))) {
      sp := sp - UInt(1)
    }
    when (sp > UInt(0)) {
      dataOut := stack_mem(sp - UInt(1))
    }
  }

  io.dataOut := dataOut
}

class StackTests(c: Stack) extends Tester(c) {  
  var nxtDataOut = 0
  val stack = new ScalaStack[Int]()

  for (t <- 0 until 16) {
    val enable  = rnd.nextInt(2)
    val push    = rnd.nextInt(2)
    val pop     = rnd.nextInt(2)
    val dataIn  = rnd.nextInt(256)
    val dataOut = nxtDataOut

    if (enable == 1) {
      if (stack.length > 0)
        nxtDataOut = stack.top
      if (push == 1 && stack.length < c.depth) {
        stack.push(dataIn)
      } else if (pop == 1 && stack.length > 0) {
        stack.pop()
      }
    }

    poke(c.io.pop,    pop)
    poke(c.io.push,   push)
    poke(c.io.en,     enable)
    poke(c.io.dataIn, dataIn)
    step(1)
    expect(c.io.dataOut, dataOut)
  }
}


