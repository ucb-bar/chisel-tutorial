// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{Driver, PeekPokeTester}
import org.scalatest.FreeSpec

import util.Random

class LifeTests(c: Life) extends PeekPokeTester(c) {
  // Disable printing when peeking state variables

  def setMode(run: Boolean): Unit = {
    poke(c.io.running, run)
    step(1)
  }

  def clear_board(): Unit = {
    poke(c.io.writeValue, 0)

    for {
      i <- 0 until c.rows
      j <- 0 until c.cols
    } {
      poke(c.io.writeRowAddress, i)
      poke(c.io.writeColAddress, j)
      step(1)
    }
  }

  def initBlinker(): Unit = {
    clear_board()

    poke(c.io.writeValue, 1)
    poke(c.io.writeRowAddress, 3)
    for(addr <- Seq(3, 5)) {
      poke(c.io.writeColAddress, addr)
      step(1)
    }
    poke(c.io.writeRowAddress, 4)
    for(addr <- Seq(4, 5)) {
      poke(c.io.writeColAddress, addr)
      step(1)
    }
    poke(c.io.writeRowAddress, 5)
    for(addr <- Seq(4)) {
      poke(c.io.writeColAddress, addr)
      step(1)
    }

  }

  def initGlider(): Unit = {
    clear_board()

    poke(c.io.writeValue, 1)
    poke(c.io.writeRowAddress, 3)
    for(addr <- Seq(3, 5)) {
      poke(c.io.writeColAddress, addr)
      step(1)
    }
    poke(c.io.writeRowAddress, 4)
    for(addr <- Seq(4, 5)) {
      poke(c.io.writeColAddress, addr)
      step(1)
    }
    poke(c.io.writeRowAddress, 5)
    for(addr <- Seq(4)) {
      poke(c.io.writeColAddress, addr)
      step(1)
    }
  }

  def randomize(): Unit = {
    clear_board()

    for(addr <- 0 until c.rows * c.rows) {
      poke(c.io.writeValue, Random.nextBoolean())
      poke(c.io.writeRowAddress, addr)
      step(1)
    }
  }

  def printBoard(): Unit = {
    // Print column number
    print("   ")
    for (i <- 0 until c.cols)
      print(" " + i.toString.last)
    println()

    for(i <- 0 until c.rows) {
      // Print line number
      print(f"$i%2d")
      print(" ")

      // Print cell state
      for {
        j <- 0 until c.cols
      } {
        val s = peek(c.io.state(i)(j))
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
  //  initBlinker
  initGlider()
  //  randomize()
  printBoard()

  setMode(run = true)

  for(time <- 0 until 100) {
    println(s"Period: $time")
    printBoard()
    step(1)
  }
}

class LifeTester extends FreeSpec {
  "life must run" in {
    Driver.execute(Array(), () => new Life(30, 30)) { c =>
      new LifeTests(c)
    } // should be (true)
  }
}
