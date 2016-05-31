package solutions

import Chisel._
import Chisel.iotesters._

object Solutions {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length)
    val res = 
    args(0) match {
      case "Accumulator" =>
        runPeekPokeTester(() => new Accumulator()){
          (c,b) => new AccumulatorTests(c,b)}
      case "LFSR16" =>
        runPeekPokeTester(() => new LFSR16()){
          (c,b) => new LFSR16Tests(c,b)}
      case "SingleEvenFilter" =>
        runPeekPokeTester(() => new SingleEvenFilter(UInt(width = 16))){
          (c,b) => new SingleEvenFilterTests(c,b)}
      case "VecShiftRegister" =>
        runPeekPokeTester(() => new VecShiftRegister()){
          (c,b) => new VecShiftRegisterTests(c,b)}
      case "VecShiftRegisterSimple" =>
        runPeekPokeTester(() => new VecShiftRegisterSimple()){
          (c,b) => new VecShiftRegisterSimpleTests(c,b)}
      case "VecShiftRegisterParam" =>
        runPeekPokeTester(() => new VecShiftRegisterParam(8, 4)){
          (c,b) => new VecShiftRegisterParamTests(c,b)}
      case "Max2" =>
        runPeekPokeTester(() => new Max2()){
          (c,b) => new Max2Tests(c,b)}
      case "MaxN" =>
        runPeekPokeTester(() => new MaxN(8, 16)){
          (c,b) => new MaxNTests(c,b)}
      case "Adder" =>
        runPeekPokeTester(() => new Adder(8)){
          (c,b) => new AdderTests(c,b)}
      case "DynamicMemorySearch" =>
        runPeekPokeTester(() => new DynamicMemorySearch(8, 4)){
          (c,b) => new DynamicMemorySearchTests(c,b)}
      case "RealGCD" => 
        runPeekPokeTester(() => new RealGCD()){
          (c,b) => new RealGCDTests(c,b)}
      case "Mux2" => 
        runPeekPokeTester(() => new Mux2()){
          (c,b) => new Mux2Tests(c,b)}
      case "Mux4" =>
        runPeekPokeTester(() => new Mux4()){
          (c,b) => new Mux4Tests(c,b)}
      case "Memo" => 
        runPeekPokeTester(() => new Memo()){
          (c,b) => new MemoTests(c,b)}
      case "Mul" => 
        runPeekPokeTester(() => new Mul()){
          (c,b) => new MulTests(c,b)}
      case "Counter" =>
        runPeekPokeTester(() => new Counter()){
          (c,b) => new CounterTest(c,b)}
      case "VendingMachine" =>
        runPeekPokeTester(() => new VendingMachine()){
          (c,b) => new VendingMachineTests(c,b)}
      case "VendingMachineSwitch" =>
        runPeekPokeTester(() => new VendingMachineSwitch()){
          (c,b) => new VendingMachineSwitchTests(c,b)}
    }

    if(!res) {
      System.exit(1)
    }
  }
}

