package TutorialSolutions

import Chisel._
import Chisel.iotesters._

object TutorialSolutions {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length)
    val res = 
    args(0) match {
      case "Accumulator" =>
        runClassicTester(() => new Accumulator()){
          (c,p) => new AccumulatorTests(c,p)}
      case "LFSR16" =>
        runClassicTester(() => new LFSR16()){
          (c,p) => new LFSR16Tests(c,p)}
      case "SingleEvenFilter" =>
        runClassicTester(() => new SingleEvenFilter(UInt(width = 16))){
          (c,p) => new SingleEvenFilterTests(c,p)}
      case "VecShiftRegister" =>
        runClassicTester(() => new VecShiftRegister()){
          (c,p) => new VecShiftRegisterTests(c,p)}
      case "VecShiftRegisterSimple" =>
        runClassicTester(() => new VecShiftRegisterSimple()){
          (c,p) => new VecShiftRegisterSimpleTests(c,p)}
      case "VecShiftRegisterParam" =>
        runClassicTester(() => new VecShiftRegisterParam(8, 4)){
          (c,p) => new VecShiftRegisterParamTests(c,p)}
      case "Max2" =>
        runClassicTester(() => new Max2()){
          (c,p) => new Max2Tests(c,p)}
      case "MaxN" =>
        runClassicTester(() => new MaxN(8, 16)){
          (c,p) => new MaxNTests(c,p)}
      case "Adder" =>
        runClassicTester(() => new Adder(8)){
          (c,p) => new AdderTests(c,p)}
      case "DynamicMemorySearch" =>
        runClassicTester(() => new DynamicMemorySearch(8, 4)){
          (c,p) => new DynamicMemorySearchTests(c,p)}
      case "RealGCD" => 
        runClassicTester(() => new RealGCD()){
          (c,p) => new RealGCDTests(c,p)}
      case "Mux2" => 
        runClassicTester(() => new Mux2()){
          (c,p) => new Mux2Tests(c,p)}
      case "Mux4" =>
        runClassicTester(() => new Mux4()){
          (c,p) => new Mux4Tests(c,p)}
      case "Memo" => 
        runClassicTester(() => new Memo()){
          (c,p) => new MemoTests(c,p)}
      case "Mul" => 
        runClassicTester(() => new Mul()){
          (c,p) => new MulTests(c,p)}
      case "Counter" =>
        runClassicTester(() => new Counter()){
          (c,p) => new CounterTest(c,p)}
      case "VendingMachine" =>
        runClassicTester(() => new VendingMachine()){
          (c,p) => new VendingMachineTests(c,p)}
      case "VendingMachineSwitch" =>
        runClassicTester(() => new VendingMachineSwitch()){
          (c,p) => new VendingMachineSwitchTests(c,p)}
    }
  }
}

