package examples


import Chisel.iotesters.{ Backend => TesterBackend, _ }
import scala.collection.mutable.HashMap
import scala.collection.mutable.{Stack => ScalaStack}
import scala.util.Random

class StackTests(c: Stack, b: Option[TesterBackend] = None) extends PeekPokeTester(c, _backend=b) {
  var nxtDataOut = 0
  var dataOut = 0
  val stack = new ScalaStack[Int]()

  for (t <- 0 until 16) {
    val enable  = rnd.nextInt(2)
    val push    = rnd.nextInt(2)
    val pop     = rnd.nextInt(2)
    val dataIn  = rnd.nextInt(256)

    if (enable == 1) {
      dataOut = nxtDataOut
      if (push == 1 && stack.length < c.depth) {
        stack.push(dataIn)
      } else if (pop == 1 && stack.length > 0) {
        stack.pop()
      }
      if (stack.length > 0) {
        nxtDataOut = stack.top
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

class StackTester extends ChiselFlatSpec {
  "Stack" should "correctly support basic stack operations" in {
    runPeekPokeTester(() => new Stack(depth = 8)) {
      (c,b) => new StackTests(c,b)
    }
  }
}



