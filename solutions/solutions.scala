package TutorialSolutions

import Chisel._

object TutorialSolutions {
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
      case "SingleEvenFilter" =>
        chiselMainTest(tutArgs, () => Module(new SingleEvenFilter(UInt(width = 16)))){
          c => new SingleEvenFilterTests(c)}
      case "VecShiftRegister" =>
        chiselMainTest(tutArgs, () => Module(new VecShiftRegister())){
          c => new VecShiftRegisterTests(c)}
      case "MaxN" =>
        chiselMainTest(tutArgs, () => Module(new MaxN(8, 16))){
          c => new MaxNTests(c)}
      case "DynamicMemorySearch" =>
        chiselMainTest(tutArgs, () => Module(new DynamicMemorySearch())){
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
      // case "FIR" =>
      //   chiselMainTest(tutArgs, () => Module(new FIR())){
      //     c => new FIRTests(c)}
    }
  }
}

