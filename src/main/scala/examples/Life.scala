// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.util.log2Ceil

import scala.util.Random

class Cell extends Module {
  val io = IO(new Bundle {
    val nbrs = Vec(8, Input(Bool()))
    val out = Output(Bool())
    val running = Input(Bool())
    val writeEnable = Input(Bool())
    val writeValue = Input(Bool())
  })
  val isAlive = RegInit(false.B)

  when(!io.running) {
    when(io.writeEnable) {
      isAlive := io.writeValue
    }
      .otherwise {
        isAlive := isAlive
      }
  }.otherwise {
    val count = io.nbrs.foldRight(0.U(3.W))((x: Bool, y: UInt) => x.asUInt + y)

    when(isAlive) {
      when(count < 2.U) {
        isAlive := false.B
      }.elsewhen(count < 4.U) {
        isAlive := true.B
      }.otherwise {
        isAlive := false.B
      }
    }.otherwise {
      when(!isAlive && count === 3.U) {
        isAlive := true.B
      }
      .otherwise {
        isAlive := false.B
      }
    }
  }

  io.out := isAlive
}

class Life(val n: Int) extends Module {
  private val tot = n * n
  val io = IO(new Bundle {
    val state = Vec(tot, Output(Bool()))
    val running = Input(Bool())
    val writeValue = Input(Bool())
    val writeAddress = Input(UInt(log2Ceil(tot+1).W))
  })
  private def idx(i: Int, j: Int) = ((j + n) % n) * n + ((i + n) % n)
  val rnd = new Random(1)

  private val cells = Range(0, tot).map(_ => Module(new Cell))

  for (k <- 0 until tot) {
    io.state(k) := cells(k).io.out
    cells(k).io.running := io.running
    cells(k).io.writeValue := io.writeValue
    cells(k).io.writeEnable := io.writeAddress === k.U
  }

  // for every cell wire it up to it's neighbors, edges wrap, world is a torus
  for (j <- 0 until n) {
    for (i <- 0 until n) {
      val cell = cells(j * n + i)
      var ni = 0
      for (dj <- -1 to 1) {
        for (di <- -1 to 1) {
          if (di != 0 || dj != 0) {
            cell.io.nbrs(ni) := cells(idx(i + di, j + dj)).io.out
            ni = ni + 1
          }
        }
      }
    }
  }
}
