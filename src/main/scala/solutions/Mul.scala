package solutions

import Chisel._
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
