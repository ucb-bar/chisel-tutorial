// See LICENSE.txt for license details.
package examples

import chisel3._

import scala.util.Random

class Cell(isBorn: Boolean) extends Module {
  val io = IO(new Bundle {
    val nbrs = Vec(8, Input(Bool()))
    val out  = Output(Bool())
  })
  val isAlive = RegInit(isBorn.B)
  val count   = io.nbrs.foldRight(0.U(3.W))((x: Bool, y: UInt) => x.asUInt + y)
  when (count < 2.U) {
    isAlive := false.B
  } .elsewhen (count < 4.U) {
    isAlive := true.B
  } .elsewhen (count >= 4.U) {
    isAlive := false.B
  } .elsewhen(!isAlive && count === 3.U) {
    isAlive := true.B
  }
  io.out := isAlive
}

class Life(val n: Int) extends Module {
  val tot = n*n
  val io = IO(new Bundle {
    val state = Vec(tot, Output(Bool()))
  })
  def idx(i: Int, j: Int) = ((j+n)%n)*n+((i+n)%n)
  val rnd = new Random(1)

  val cells = Range(0, tot).map(i => Module(new Cell(rnd.nextInt(2) == 1)))
  for (k <- 0 until tot)
    io.state(k) := cells(k).io.out
  for (j <- 0 until n) {
    for (i <- 0 until n) {
      val cell = cells(j*n + i)
      var ni = 0
      for (dj <- -1 to 1) {
        for (di <- -1 to 1) {
          if (di != 0 || dj != 0) {
            cell.io.nbrs(ni) := cells(idx(i+di, j+dj)).io.out
            ni = ni + 1
          }
        }
      }
    }
  }
}
