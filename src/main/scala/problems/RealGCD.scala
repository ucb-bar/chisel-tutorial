// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.{Valid, DeqIO}

class RealGCDInput extends Bundle {
  val a = UInt(16.W)
  val b = UInt(16.W)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(16.W)))
  })

  // flush this out ...

}
