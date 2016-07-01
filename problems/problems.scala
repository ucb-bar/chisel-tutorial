// See LICENSE.txt for license details.
package TutorialProblems

import Chisel._

object TutorialProblems {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length) 
    val res = 
    args(0) match {
      case "Accumulator" =>
        chiselMainTest(tutArgs, () => Module(new Accumulator())){
          c => new AccumulatorTests(c)}
      case "LFSR16" =>
        chiselMainTest(tutArgs, () => Module(new LFSR16())){
          c => new LFSR16Tests(c)}
      case "VecShiftRegister" =>
        chiselMainTest(tutArgs, () => Module(new VecShiftRegister())){
          c => new VecShiftRegisterTests(c)}
      case "VecShiftRegisterSimple" =>
        chiselMainTest(tutArgs, () => Module(new VecShiftRegisterSimple())){
          c => new VecShiftRegisterSimpleTests(c)}
      case "VecShiftRegisterParam" =>
        chiselMainTest(tutArgs, () => Module(new VecShiftRegisterParam(6, 8))){
          c => new VecShiftRegisterParamTests(c)}
      case "SingleEvenFilter" =>
        chiselMainTest(tutArgs, () => Module(new SingleEvenFilter(UInt(width = 16)))){
          c => new SingleEvenFilterTests(c)}
      case "MaxN" =>
        chiselMainTest(tutArgs, () => Module(new MaxN(8, 16))){
          c => new MaxNTests(c)}
      case "Max2" =>
        chiselMainTest(tutArgs, () => Module(new Max2())){
          c => new Max2Tests(c)}
      case "Adder" =>
        chiselMainTest(tutArgs, () => Module(new Adder(8))){
          c => new AdderTests(c)}
      case "DynamicMemorySearch" =>
        chiselMainTest(tutArgs, () => Module(new DynamicMemorySearch(8, 4))){
          c => new DynamicMemorySearchTests(c)}
      case "RealGCD" => 
        chiselMainTest(tutArgs, () => Module(new RealGCD())){
          c => new RealGCDTests(c)}
      case "Mux2" => 
        chiselMainTest(tutArgs, () => Module(new Mux2())){
          c => new Mux2Tests(c)}
      case "Mux4" =>
        chiselMainTest(tutArgs, () => Module(new Mux4())){
          c => new Mux4Tests(c)}
      case "Memo" => 
        chiselMainTest(tutArgs, () => Module(new Memo())){
          c => new MemoTests(c)}
      case "Mul" => 
        chiselMainTest(tutArgs, () => Module(new Mul())){
          c => new MulTests(c)}
      case "Counter" =>
        chiselMainTest(tutArgs, () => Module(new Counter())){
          c => new CounterTest(c)}
      case "VendingMachine" =>
        chiselMainTest(tutArgs, () => Module(new VendingMachine())){
          c => new VendingMachineTests(c)}
    }
  }
}

