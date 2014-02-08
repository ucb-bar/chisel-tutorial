package TutorialSolutions

import Chisel._
import scala.collection.mutable.HashMap
import scala.util.Random

class VendingMachine extends Module {
  val io = new Bundle {
    val nickel = Bool(dir = INPUT)
    val dime   = Bool(dir = INPUT)
    val valid  = Bool(dir = OUTPUT) }
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil =
    Enum(UInt(), 5)
  val state = Reg(init=sIdle)
  when (state === sIdle) {
    when (io.nickel) { state := s5 }
    when (io.dime)   { state := s10 }
  }
  when (state === s5) {
    when (io.nickel) { state := s10 }
    when (io.dime)   { state := s15 }
  }
  when (state === s10) {
    when (io.nickel) { state := s15 }
    when (io.dime)   { state := sOk }
  }
  when (state === s15) {
    when (io.nickel) { state := sOk }
    when (io.dime)   { state := sOk }
  }
  when (state === sOk) {
    state := sIdle
  }
  io.valid := (state === sOk)
}

class VendingMachineTests(c: VendingMachine) extends Tester(c, Array(c.io)) {
  defTests {
    var allGood = true
    val vars    = new HashMap[Node, Node]()
    val rnd     = new Random()
    var money   = 0
    for (t <- 0 until 20) {
      vars.clear()
      val coin          = rnd.nextInt(3)*5
      val isNickel      = coin == 5
      val isDime        = coin == 10
      vars(c.io.nickel) = Bool(isNickel)
      vars(c.io.dime)   = Bool(isDime)
      val isValid       = money >= 20
      if (isValid) {money = 0} else {money = money + coin}
      vars(c.io.valid)  = Bool(isValid)
      allGood           = step(vars) && allGood
    }
    allGood
  }
}
