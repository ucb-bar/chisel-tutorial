package problems

import Chisel._
import Chisel.iotesters._

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

class VendingMachineTests(c: VendingMachine, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  var money = 0
  var isValid = false
  for (t <- 0 until 20) {
    val coin     = rnd.nextInt(3)*5
    val isNickel = coin == 5
    val isDime   = coin == 10

    // Advance circuit
    poke(c.io.nickel, if (isNickel) 1 else 0)
    poke(c.io.dime,   if (isDime) 1 else 0)
    step(1)

    // Advance model
    money = if (isValid) 0 else (money + coin)
    isValid = money >= 20

    // Compare
    expect(c.io.valid, if (isValid) 1 else 0)
  }
}
