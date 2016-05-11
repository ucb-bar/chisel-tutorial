package TutorialSolutions

import Chisel._
import Chisel.iotesters._

object TutorialSolutions {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length) 
    val res = 
    args(0) match {
      case "Accumulator" =>
        chiselMainTest(tutArgs, () => new Accumulator()){
          c => new AccumulatorTests(c)}
      case "LFSR16" =>
        chiselMainTest(tutArgs, () => new LFSR16()){
          c => new LFSR16Tests(c)}
      case "SingleEvenFilter" =>
        chiselMainTest(tutArgs, () => new SingleEvenFilter(UInt(width = 16))){
          c => new SingleEvenFilterTests(c)}
      case "VecShiftRegister" =>
        chiselMainTest(tutArgs, () => new VecShiftRegister()){
          c => new VecShiftRegisterTests(c)}
      case "VecShiftRegisterSimple" =>
        chiselMainTest(tutArgs, () => new VecShiftRegisterSimple()){
          c => new VecShiftRegisterSimpleTests(c)}
      case "VecShiftRegisterParam" =>
        chiselMainTest(tutArgs, () => new VecShiftRegisterParam(8, 4)){
          c => new VecShiftRegisterParamTests(c)}
      case "Max2" =>
        chiselMainTest(tutArgs, () => new Max2()){
          c => new Max2Tests(c)}
      case "MaxN" =>
        chiselMainTest(tutArgs, () => new MaxN(8, 16)){
          c => new MaxNTests(c)}
      case "Adder" =>
        chiselMainTest(tutArgs, () => new Adder(8)){
          c => new AdderTests(c)}
      case "DynamicMemorySearch" =>
        chiselMainTest(tutArgs, () => new DynamicMemorySearch(8, 4)){
          c => new DynamicMemorySearchTests(c)}
      case "RealGCD" => 
        chiselMainTest(tutArgs, () => new RealGCD()){
          c => new RealGCDTests(c)}
      case "Mux2" => 
        chiselMainTest(tutArgs, () => new Mux2()){
          c => new Mux2Tests(c)}
      case "Mux4" =>
        chiselMainTest(tutArgs, () => new Mux4()){
          c => new Mux4Tests(c)}
      case "Memo" => 
        chiselMainTest(tutArgs, () => new Memo()){
          c => new MemoTests(c)}
      case "Mul" => 
        chiselMainTest(tutArgs, () => new Mul()){
          c => new MulTests(c)}
      case "Counter" =>
        chiselMainTest(tutArgs, () => new Counter()){
          c => new CounterTest(c)}
      case "VendingMachine" =>
        chiselMainTest(tutArgs, () => new VendingMachine()){
          c => new VendingMachineTests(c)}
      case "VendingMachineSwitch" =>
        chiselMainTest(tutArgs, () => new VendingMachineSwitch()){
          c => new VendingMachineSwitchTests(c)}
    }
  }
}

