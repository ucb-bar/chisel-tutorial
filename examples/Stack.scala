package TutorialExamples

import Chisel._
import scala.collection.mutable.HashMap
import scala.collection.mutable.{Stack => ScalaStack}
import scala.util.Random

class Stack(val depth: Int) extends Module {
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
  
  when(io.en && (sp >= UInt(0))) {
    dataOut := stack_mem(sp)
  }

  io.dataOut := dataOut
}

class StackTests(c: Stack) extends Tester(c, Array(c.io)) {  
  var nxtDataOut = 0
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    val stack   = new ScalaStack[Int]()

    for (t <- 0 until 16) {
      var enable  = rnd.nextInt(2) == 0
      var push    = rnd.nextInt(2) == 0
      var pop     = rnd.nextInt(2) == 0
      var dataIn  = rnd.nextInt(256)
      var dataOut = nxtDataOut

      if (enable) {
        if (stack.length > 0)
          nxtDataOut = stack.top
        if (push && stack.length < c.depth) {
          stack.push(dataIn)
        } else if (pop && stack.length > 0) {
          stack.pop()
        }
      }

      vars(c.io.pop)     = Bool(pop)
      vars(c.io.push)    = Bool(push)
      vars(c.io.en)      = Bool(enable)
      vars(c.io.dataIn)  = UInt(dataIn)
      vars(c.io.dataOut) = UInt(dataOut)

      allGood = step(vars) && allGood
    }
    allGood
  }
}


