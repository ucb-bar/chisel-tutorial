package TutorialProblems

import Chisel._

class VendingMachine extends Module {
  val io = new Bundle {
    val nickel = Bool(INPUT)
    val dime   = Bool(INPUT)
    val valid  = Bool(OUTPUT) }
  val sIdle :: s5 :: s10 :: s15 :: sOk :: Nil = 
    Enum(UInt(), 5)
  val state = Reg(init=sIdle)

  // flush it out ...
  state := s5
  io.valid := (state === sOk)
}

class VendingMachineTests(c: VendingMachine) extends Testy(c) {  
  var money   = 0
  for (t <- 0 until 20) {
    val coin     = rnd.nextInt(3)*5
    val isNickel = coin == 5
    val isDime   = coin == 10
    poke(c.io.nickel, Bool(isNickel).litValue())
    poke(c.io.dime, Bool(isDime).litValue())
    step(1)
    val isValid = money >= 20
    expect(c.io.valid, Bool(isValid).litValue())
    money = if (isValid) 0 else (money + coin)
  }
}
