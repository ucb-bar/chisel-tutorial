package Tutorial

import Chisel._
import scala.collection.mutable.HashMap
import scala.util.Random

class VendingMachine extends Component {
  val io = new Bundle {
    val nickel = Bool(INPUT)
    val dime   = Bool(INPUT)
    val valid  = Bool(OUTPUT) }
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil = 
    Enum(5){ UFix() }
  val state = Reg(resetVal = sIdle)

  // flush it out ...

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
      vars(c.io.valid)  = Bool(isValid)
      allGood           = step(vars) && allGood
      if (t > 0)
        money           = if (isValid) 0 else (money + coin)
    }
    allGood
  }
}
