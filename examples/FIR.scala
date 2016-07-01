// See LICENSE.txt for license details.
package TutorialExamples

import Chisel._
import scala.collection.mutable.ArrayBuffer

object Delays {
  def apply[U <: Data](x: U, n: Int, i: U): List[U] = 
    if (n <= 1) List(x) else x :: Delays(RegNext(x, init=i), n-1, i)
}

object GenFIR {
  def apply[T <: Data with Num[T]](ws: Seq[T], x: T, i: T): T = 
    (ws, Delays(x, ws.length, i)).zipped.map( _ * _ ).reduce( _ + _ )
}

class FIR extends Module {
  val io = new Bundle { val x = Flo(INPUT); val z = Flo(OUTPUT) }
  val ws = Array(Flo(0.25), Flo(0.75))
  io.z  := GenFIR(ws, io.x, Flo(0))
}

// FIR filter
/*
class FIR extends Module {
  val io = new Bundle {
    val x   = Flo(INPUT)
    val z   = Flo(OUTPUT)
  }
  // io.z := io.x * Flo(0.25) + Reg(init = Flo(0.0), next = io.x) * Flo(0.75)
  printf("IO.IN FLO %e      X\n", io.x);
  printf("IO.IN BIT %d      Y\n", io.x);
  io.z := io.x

  // -------------------------------- \\
}
*/

class FIRTests(c: FIR) extends Tester(c) {
  var px = 0.0f
  for (i <- 0 until 10) {
    val x = rnd.nextFloat()
    poke(c.io.x, x)
    val res = x * c.ws(0).floLitValue + px * c.ws(1).floLitValue
    println("TST X = " + x + " RES = " + res);
    expect(c.io.z, res)
    step(1)
    px  = x
  }
}
