package Tutorial

import Chisel._
import scala.collection.mutable.HashMap
import util.Random

class Cell(isBorn: Boolean) extends Component {
  val io = new Bundle {
    val nbrs = Vec(8){ Bool(INPUT) }
    val out  = Bool(OUTPUT)
  }
  val isAlive = Reg(resetVal = Bool(isBorn))
  val count   = io.nbrs.foldRight(UFix(0, 3))((x: Bool, y: UFix) => x.toUFix + y)
  when (count < UFix(2)) {
    isAlive := Bool(false)
  } .elsewhen (count < UFix(4)) {
    isAlive := Bool(true)
  } .elsewhen (count >= UFix(4)) {
    isAlive := Bool(false)
  } .elsewhen(!isAlive && count === UFix(3)) {
    isAlive := Bool(true)
  }
  io.out := isAlive
}

class Life(val n: Int) extends Component {
  val tot = n*n
  val io = new Bundle {
    val state = Vec(tot){ Bool(OUTPUT) }
  }
  def idx(i: Int, j: Int) = ((j*n+n)%n)+((i+n)%n)
  def nbrIdx(di: Int, dj: Int) = (dj+1)*3 + (di+1)
  val rnd = new Random()
  val cells = Range(0, tot).map(i => new Cell(rnd.nextInt(2) == 1))
  for (k <- 0 until tot)
    io.state(k) := cells(k).io.out
  for (j <- 0 until n) {
    for (i <- 0 until n) {
      val cell = cells(j*n + i)
      for (dj <- -1 until 1) {
        for (di <- -1 until 1) {
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

class LifeTests(c: Life) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val ovars   = new HashMap[Node, Node]()
    var tot     = 0
    for (t <- 0 until 16) {
      vars.clear()
      step(vars, ovars)
      for (j <- 0 until c.n) {
        for (i <- 0 until c.n) {
          print(ovars(c.io.state(c.idx(i, j))).litValue())
        }
        println()
      }
    }
    // TODO: WRITE REAL TEST SUITE
    allGood
  }
}
