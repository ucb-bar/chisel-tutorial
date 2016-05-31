package solutions

import Chisel._
import Chisel.iotesters._
import scala.collection.mutable.ArrayBuffer

/** Four-by-four multiply using a look-up table.
*/
class Mul extends Module {
  val io = new Bundle {
    val x   = UInt(INPUT,  4)
    val y   = UInt(INPUT,  4)
    val z   = UInt(OUTPUT, 8)
  }
  val muls = new ArrayBuffer[UInt]()

  // -------------------------------- \\
  // Calculate io.z = io.x * io.y by
  // building filling out muls
  // -------------------------------- \\

  for (i <- 0 until 16)
    for (j <- 0 until 16)
      muls += UInt(i * j, width = 8)
  val tbl = Vec(muls)
  io.z := tbl((io.x << UInt(4)) | io.y)

  // -------------------------------- \\
}

class MulTests(c: Mul, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  val maxInt  = 1 << 4
  for (i <- 0 until 10) {
    val x = rnd.nextInt(maxInt)
    val y = rnd.nextInt(maxInt)
    poke(c.io.x, x)
    poke(c.io.y, y)
    step(1)
    expect(c.io.z, (x * y))
  }
}
