// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.util.log2Ceil

class Cell extends Module {
  //noinspection TypeAnnotation
  val io = IO(new Bundle {
    val neighbors = Vec(8, Input(Bool()))
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
    val count = io.neighbors.foldRight(0.U(3.W))((x: Bool, y: UInt) => x.asUInt + y)

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

class Life(val rows: Int, val cols: Int) extends Module {
  //noinspection TypeAnnotation
  val io = IO(new Bundle {
    val state = Output(Vec(rows, Vec(cols, Bool())))
    val running = Input(Bool())
    val writeValue = Input(Bool())
    val writeRowAddress = Input(UInt(log2Ceil(rows+1).W))
    val writeColAddress = Input(UInt(log2Ceil(cols+1).W))
  })

  private val cells = Array.fill(rows, cols)(Module(new Cell))

  for {
    row <- 0 until rows
    col <- 0 until cols
  } {
    io.state(row)(col) := cells(row)(col).io.out
    cells(row)(col).io.running := io.running
    cells(row)(col).io.writeValue := io.writeValue
    cells(row)(col).io.writeEnable := io.writeRowAddress === row.U & io.writeColAddress === col.U
  }

  def getNeighborIndex(row: Int, rowDelta: Int, col: Int, colDelta: Int): (Int, Int) = {
    def wrapIndex(index: Int, delta: Int, max: Int): Int = {
      if(index == 0 && delta == -1) { max - 1 }
      else if(index == max - 1 && delta == 1) { 0 }
      else { index + delta }
    }
    (wrapIndex(row, rowDelta, rows), wrapIndex(col, colDelta, cols))
  }

  // for every cell wire it up to it's neighbors, edges wrap, world is a torus
  for (row <- 0 until rows) {
    for (col <- 0 until cols) {
      val cell = cells(row)(col)
      var neighborInput = 0
      for (deltaRow <- -1 to 1) {
        for (deltaCol <- -1 to 1) {
          if (deltaRow != 0 || deltaCol != 0) {
            val (rowIndex, colIndex) = getNeighborIndex(row, deltaRow, col, deltaCol)
            cell.io.neighbors(neighborInput) := cells(rowIndex)(colIndex).io.out
            neighborInput = neighborInput + 1
          }
        }
      }
    }
  }
}