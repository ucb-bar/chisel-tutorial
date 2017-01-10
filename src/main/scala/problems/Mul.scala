// See LICENSE.txt for license details.
package problems

import chisel3._
import scala.collection.mutable.ArrayBuffer

// Problem:
//
// Implement 'z' = 'x' * 'y' by
// building filling out muls
//
class Mul extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(4.W))
    val y   = Input(UInt(4.W))
    val z   = Output(UInt(8.W))
  })
  val muls = new ArrayBuffer[UInt]()

  // Implement below ----------

  // Implement above ----------
}
