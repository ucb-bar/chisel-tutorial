// See LICENSE.txt for license details.
package examples


import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

import util.Random

class LifeTests(c: Life) extends PeekPokeTester(c) {
//  for (t <- 0 until 16) {
//    step(1)
//    for (j <- 0 until c.n) {
//      for (i <- 0 until c.n) {
//        peek(c.io.state(c.idx(i, j)))
//      }
//      println()
//    }
//  }
  // Disable printing when peeking state variables

  def setMode(run: Boolean): Unit = {
    poke(c.io.running, run)
    step(1)
  }

  def clear_board(): Unit = {
    poke(c.io.writeValue, 0)

    for (i <- 0 until c.n*c.n) {
      // println(s"clearing $i")
      poke(c.io.writeAddress, i)
      step(1)
    }
  }

  def init_blinker(): Unit = {
    clear_board()

    poke(c.io.writeValue, 1)
//    for(addr <- Seq(44, 45, 46)) {
    for(addr <- Seq(14, 15, 16)) {
      poke(c.io.writeAddress, addr)
      step(1)
    }
  }

  def init_glider(): Unit = {
    clear_board()

    poke(c.io.writeValue, 1)
    for(addr <- Seq(2, 15, 25, 26, 27)) {
      poke(c.io.writeAddress, addr)
      step(1)
    }
  }

  def randomize(): Unit = {
    clear_board()

    for(addr <- 0 until c.n * c.n) {
      poke(c.io.writeValue, Random.nextBoolean())
      poke(c.io.writeAddress, addr)
      step(1)
    }
  }

  def printBoard(): Unit = {
    // Print column number
    print("   ")
    for (j <- 0 until c.n)
      print(" " + j.toString.last)
    println()

    for (j <- 0 until c.n) {
      // Print line number
      print(f"$j%2d")
      print(" ")

      // Print cell state
      for (i <- 0 until c.n) {
        val s = peek(c.io.state(j*c.n+i))
        if (s == 1)
          print(" *")
        else
          print("  ")
      }

      println()
    }
    println()
  }

  setMode(run = false)
  // uncomment one of these
//  init_blinker
  init_glider()
//  randomize()
  printBoard()

  setMode(run = true)

  for(time <- 0 until 100) {
    println(s"Period: $time")
    printBoard()
    step(1)
  }
}

class LifeTester extends ChiselFlatSpec {
  behavior of "Life"
  backends foreach {backend =>
    it should s"implement transition rules for Conway's life game in $backend" in {
      Driver.execute(Array(), () => new Life(12)) { c =>
        new LifeTests(c)
      } should be (true)
    }
  }
}

