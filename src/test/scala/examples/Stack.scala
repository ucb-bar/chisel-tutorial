// See LICENSE.txt for license details.
package examples


import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}
import scala.collection.mutable.HashMap
import scala.collection.mutable.{Stack => ScalaStack}
import scala.util.Random

class StackTests(c: Stack) extends PeekPokeTester(c) {
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
  behavior of "Stack"
  backends foreach {backend =>
    it should s"correctly support basic stack operations $backend" in {
      Driver(() => new Stack(depth = 8), backend)((c) => new StackTests(c)) should be (true)
    }
  }
}



