package TutorialExamples

import Chisel._
import scala.collection.mutable.ArrayBuffer

object Delays {
  def apply[U <: Data](x: U, n: Int): List[U] = 
    if (n <= 1) List(x) else x :: Delays(RegNext(x), n-1)
}

object GenFIR {
  def apply[T <: Data with Num[T]](ws: Seq[T], x: T): T = 
    (ws, Delays(x, ws.length)).zipped.map( _ * _ ).reduce( _ + _ )
}

class FIR extends Module {
  val io = new Bundle { val x = Flo(INPUT); val z = Flo(OUTPUT) }
  val ws = Array(Flo(0.25), Flo(0.75))
  io.z  := GenFIR(ws, io.x)
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
    val lx = Flo(x)
    poke(c.io.x, lx.litValue())
    val res = x * c.ws(0).floLitValue + px * c.ws(1).floLitValue
    println("TST X = " + x + " Flo(x) " + lx.floLitValue + " RES = " + res);
    val expectedFloat = java.lang.Float.intBitsToFloat(Flo(res).litValue().toInt)
    expect(c.io.z, expectedFloat)
    step(1)
    px  = x
  }
}
