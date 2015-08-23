package TutorialExamples

import Chisel._
import util.Random

class Cell(isBorn: Boolean) extends Module {
  val io = new Bundle {
    val nbrs = Vec.fill(9){ Bool(INPUT) }
    val out  = Bool(OUTPUT)
  }
  val isAlive = Reg(init=Bool(isBorn))
  val count   = io.nbrs.foldRight(UInt(0, 3))((x: Bool, y: UInt) => x.toUInt + y)
  when (count < UInt(2)) {
    isAlive := Bool(false)
  } .elsewhen (isAlive && count < UInt(4)) {
    isAlive := Bool(true)
  } .elsewhen (count >= UInt(4)) {
    isAlive := Bool(false)
  } .elsewhen(!isAlive && count === UInt(3)) {
    isAlive := Bool(true)
  }
  io.out := isAlive
}

class Life(val n: Int) extends Module {
  val tot = n*n
  val io = new Bundle {
    val state = Vec.fill(tot){ Bool(OUTPUT) }
  }
  def idx(i: Int, j: Int) = ((j*n+n*n)%(n*n))+((i+n)%n)
  def nbrIdx(di: Int, dj: Int) = (dj+1)*3 + (di+1)
  val rnd = new Random()
  val cells = Range(0, tot).map(i => Module(new Cell(rnd.nextInt(2) == 1)))
  for (k <- 0 until tot)
    io.state(k) := cells(k).io.out
  for (j <- 0 until n) {
    for (i <- 0 until n) {
      val cell = cells(j*n + i)
      for (dj <- -1 to 1) {
        for (di <- -1 to 1) {
          val ni = nbrIdx(di, dj)
          if (di == 0 && dj == 0)
            cell.io.nbrs(ni) := Bool(false)
          else
            cell.io.nbrs(ni) := cells(idx(i+di, j+dj)).io.out
        }
      }
    }
  }
}

class LifeTests(c: Life) extends Tester(c) {

  // Disable printing when peeking state variables
  this.isTrace = false

  for (t <- 0 until 16) {
    // Print column number
    print("  ")
    for (j <- 0 until c.n)
      print(j)
    println()

    for (j <- 0 until c.n) {
      // Print line number
      print(j)
      print(" ")

      // Print cell state
      for (i <- 0 until c.n) {
        print(peek(c.io.state(j*c.n+i)))
      }

      println()
    }

    println()
    step(1)
  }
}
