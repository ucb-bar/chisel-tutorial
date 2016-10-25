// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.{Valid, DeqIO}

class RealGCDInput extends Bundle {
  val a = Bits(width = 16)
  val b = Bits(width = 16)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(width = 16)))
  })

  // flush this out ...

}
