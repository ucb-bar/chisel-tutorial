// See LICENSE.txt for license details.
package problems

import chisel3._
import scala.collection.mutable.ArrayBuffer

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


  // -------------------------------- \\
}
