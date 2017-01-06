// See LICENSE for license details.

package examples

import chisel3._
import chisel3.iotesters.{PeekPokeTester, TesterOptionsManager}
import org.scalatest.{FreeSpec, Matchers}

/**
  * Sorts a Vec of elements supporting less-than comparator instantly
 *
  * @param numberOfElements  number of elements to be sorted
  * @param elementGenerator  generator for kind of elements to be sorted
  */
class SwapSorter(val numberOfElements: Int, elementGenerator: => UInt) extends Module {
  def isEven(n: Int): Boolean = n % 2 == 0
  def isOdd(n: Int):  Boolean = ! isEven(n)

  val io = IO(new Bundle {
    val in = Input(Vec(numberOfElements, elementGenerator))
    val out = Output(Vec(numberOfElements, elementGenerator))
  })

  private val swappers = Array(io.in) ++ Array.fill(numberOfElements-1)(Wire(Vec(numberOfElements, elementGenerator))) ++ Array(io.out)

  swappers.sliding(2).map(_.toList).zipWithIndex.foreach {
    case (sourceRow :: targetRow :: Nil, index) =>
      val pairedRows = if(isEven(index)) {
        // on even rows we start comparing pairs of elements starting at head
        sourceRow.zip(targetRow)
      }
      else {
        // on even rows we scoot all our comparisons over by one
        if(isEven(numberOfElements)) {
          // if there is an even number of elements then lop head and last
          sourceRow.tail.reverse.tail.reverse.zip(targetRow.tail.reverse.tail.reverse)
        }
        else {
          // otherwise just lop of the head
          sourceRow.tail.zip(targetRow.tail)

        }
      }

      val swapper = pairedRows.grouped(2).map(_.toList)
      swapper.foreach {
        case a :: b :: Nil =>
          val (source1, target1) = a
          val (source2, target2) = b
          target1 := Mux(source1 < source2, source1, source2)
          target2 := Mux(source1 < source2, source2, source1)
        case _=>
      }

      // following handles the edges of the swapping matrix
      if(isEven(index)) {
        if(isOdd(numberOfElements)) {
          //even row with odd elements, nothing to compare last with, so just copy it to next row
          targetRow.last := sourceRow.last
        }
      }
      else {
        // on odd rows we have to pass the first element straight down
        targetRow.head := sourceRow.head
        if(isEven(numberOfElements)) {
          // odd row with even elements, copy down last element too
          targetRow.last := sourceRow.last
        }
      }
    case _=>
  }
}

/**
  * Feeds a list of numbers in reverse sorted order to @param c
  * This should be worse case scenario, first has to migrate all the way to last
  * and vice versa
  *
  * @param c circuit under test
  */
class TestReversedArray(c: SwapSorter) extends PeekPokeTester(c) {
  private val numberOfElements = c.numberOfElements

  private val ins = Array.tabulate(numberOfElements) { x => BigInt(x*2) }.reverse

  ins.zipWithIndex.foreach { case (value, index) =>
    poke(c.io.in(index), value)
  }

  println(s"reversed ins ${ins.mkString(",")}")

  private val outs = (0 until numberOfElements).map { x => peek(c.io.out(x)) }
  println(s"sorted outs  ${outs.mkString(",")}")

  ins.toList.sorted.zipWithIndex.foreach { case (value, index) =>
    expect(c.io.out(index), value)
  }

  outs.zip(outs.tail).forall { case (x, y) =>
    x < y
  }
}

/**
  * Feeds a random list of numbers into circuit
  * @param c device under test
  */
class SortRandomNumbers(c: SwapSorter) extends PeekPokeTester(c) {
//  rnd.setSeed(0L)

  private val numberOfElements = c.numberOfElements

  private val ins = (0 until numberOfElements).map { _ => BigInt(rnd.nextInt(512)) }
  ins.zipWithIndex.foreach { case (b, i) => poke(c.io.in(i), b) }
  println(s"random ins  ${ins.mkString(",")}")


  private val outs = (0 until numberOfElements).map { x => peek(c.io.out(x)) }
  println(s"sorted outs ${outs.mkString(",")}")

  ins.toList.sorted.zipWithIndex.foreach { case (value, index) =>
    expect(c.io.out(index), value)
  }

  outs.zip(outs.tail).forall { case (x, y) =>
    x < y
  }
}

class SwapSorterSpec extends FreeSpec with Matchers {
  "Swap sorter sorts an input Vector into an output vector" - {
    "should work with even sized vectors" - {
      for(size <- 2 to 10 by 2) {
        s"sort $size elements" in {
          iotesters.Driver.execute(() => new SwapSorter(size, UInt(16.W)), new TesterOptionsManager) { c =>
            new SortRandomNumbers(c)
          } should be(true)
        }
        s"evens should work with $size reversed inputs which is worst case scenario" in {
          iotesters.Driver.execute(() => new SwapSorter(size, UInt(16.W)), new TesterOptionsManager) { c =>
            new TestReversedArray(c)
          } should be(true)
        }
      }
    }
    "should work with odd sized vectors" - {
      for(size <- 3 to 11 by 2) {
        s"sort $size elements" in {
          iotesters.Driver.execute(() => new SwapSorter(size, UInt(16.W)), new TesterOptionsManager) { c =>
            new SortRandomNumbers(c)
          } should be(true)
        }
        s"odds should work with $size reversed inputs which is worst case scenario" in {
          iotesters.Driver.execute(() => new SwapSorter(size, UInt(16.W)), new TesterOptionsManager) { c =>
            new TestReversedArray(c)
          } should be(true)
        }
      }
    }
    "should work with a medium sized vectors" in {
      iotesters.Driver.execute(() => new SwapSorter(50, UInt(16.W)), new TesterOptionsManager) { c =>
        new SortRandomNumbers(c)
      } should be(true)
    }
  }
}
