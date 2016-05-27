package TutorialSolutions

import Chisel._
import Chisel.iotesters._

object TutorialSolutions {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length)
    val res = 
    args(0) match {
      case "Accumulator" =>
        runPeekPokeTester(() => new Accumulator()){
          (c,p) => new AccumulatorTests(c,p)}
      case "LFSR16" =>
        runPeekPokeTester(() => new LFSR16()){
          (c,p) => new LFSR16Tests(c,p)}
      case "SingleEvenFilter" =>
        runPeekPokeTester(() => new SingleEvenFilter(UInt(width = 16))){
          (c,p) => new SingleEvenFilterTests(c,p)}
      case "VecShiftRegister" =>
        runPeekPokeTester(() => new VecShiftRegister()){
          (c,p) => new VecShiftRegisterTests(c,p)}
      case "VecShiftRegisterSimple" =>
        runPeekPokeTester(() => new VecShiftRegisterSimple()){
          (c,p) => new VecShiftRegisterSimpleTests(c,p)}
      case "VecShiftRegisterParam" =>
        runPeekPokeTester(() => new VecShiftRegisterParam(8, 4)){
          (c,p) => new VecShiftRegisterParamTests(c,p)}
      case "Max2" =>
        runPeekPokeTester(() => new Max2()){
          (c,p) => new Max2Tests(c,p)}
      case "MaxN" =>
        runPeekPokeTester(() => new MaxN(8, 16)){
          (c,p) => new MaxNTests(c,p)}
      case "Adder" =>
        runPeekPokeTester(() => new Adder(8)){
          (c,p) => new AdderTests(c,p)}
      case "DynamicMemorySearch" =>
        runPeekPokeTester(() => new DynamicMemorySearch(8, 4)){
          (c,p) => new DynamicMemorySearchTests(c,p)}
      case "RealGCD" => 
        runPeekPokeTester(() => new RealGCD()){
          (c,p) => new RealGCDTests(c,p)}
      case "Mux2" => 
        runPeekPokeTester(() => new Mux2()){
          (c,p) => new Mux2Tests(c,p)}
      case "Mux4" =>
        runPeekPokeTester(() => new Mux4()){
          (c,p) => new Mux4Tests(c,p)}
      case "Memo" => 
        runPeekPokeTester(() => new Memo()){
          (c,p) => new MemoTests(c,p)}
      case "Mul" => 
        runPeekPokeTester(() => new Mul()){
          (c,p) => new MulTests(c,p)}
      case "Counter" =>
        runPeekPokeTester(() => new Counter()){
          (c,p) => new CounterTest(c,p)}
      case "VendingMachine" =>
        runPeekPokeTester(() => new VendingMachine()){
          (c,p) => new VendingMachineTests(c,p)}
      case "VendingMachineSwitch" =>
        runPeekPokeTester(() => new VendingMachineSwitch()){
          (c,p) => new VendingMachineSwitchTests(c,p)}
    }

    if(!res) {
      System.exit(1)
    }
  }
}

