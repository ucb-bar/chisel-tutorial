// See LICENSE.txt for license details.
package solutions

import chisel3._
import scala.collection.mutable.ArrayBuffer

/** Four-by-four multiply using a look-up table.
*/
class Mul extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(width = 4))
    val y   = Input(UInt(width = 4))
    val z   = Output(UInt(width = 8))
  })
  val muls = new ArrayBuffer[UInt]()

  // -------------------------------- \\
  // Calculate io.z = io.x * io.y by
  // building filling out muls
  // -------------------------------- \\

  for (i <- 0 until 16)
    for (j <- 0 until 16)
      muls += UInt(i * j, width = 8)
  val tbl = Vec(muls)
  io.z := tbl((io.x << 4.U) | io.y)

  // -------------------------------- \\
}
