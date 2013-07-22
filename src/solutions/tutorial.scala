package TutorialSolutions

import Chisel._

object TutorialSolutions {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length) 
    val res = 
    args(0) match {
      case "GCD" => 
        chiselMainTest(tutArgs, () => Module(new GCD())){
          c => new GCDTests(c)}
      case "RealGCD" => 
        chiselMainTest(tutArgs, () => Module(new RealGCD())){
          c => new RealGCDTests(c)}
      case "Combinational" => 
        chiselMainTest(tutArgs, () => Module(new Combinational())){
          c => new CombinationalTests(c)}
      case "Functional" => 
        chiselMainTest(tutArgs, () => Module(new Functional())){
          c => new FunctionalTests(c)}
      case "Mux2" => 
        chiselMainTest(tutArgs, () => Module(new Mux2())){
          c => new Mux2Tests(c)}
      case "Mux4" =>
        chiselMainTest(tutArgs, () => Module(new Mux4())){
          c => new Mux4Tests(c)}
      case "Accumulator" => 
        chiselMainTest(tutArgs, () => Module(new Accumulator())){
          c => new AccumulatorTests(c)}
      case "Parity" => 
        chiselMainTest(tutArgs, () => Module(new Parity())){
          c => new ParityTests(c)}
      case "Memo" => 
        chiselMainTest(tutArgs, () => Module(new Memo())){
          c => new MemoTests(c)}
      case "Filter" => 
        chiselMainTest(tutArgs, () => Module(new Filter())){
          c => new FilterTests(c)}
      case "Tbl" => 
        chiselMainTest(tutArgs, () => Module(new Tbl())){
          c => new TblTests(c)}
      case "Life" => 
        chiselMainTest(tutArgs, () => Module(new Life(3))){
          c => new LifeTests(c)}
      case "Mul" => 
        chiselMainTest(tutArgs, () => Module(new Mul())){
          c => new MulTests(c)}
      case "Risc" => 
        chiselMainTest(tutArgs, () => Module(new Risc())){
          c => new RiscTests(c)}
      case "Counter" =>
        chiselMainTest(tutArgs, () => Module(new Counter())){
          c => new CounterTest(c)}
      case "VendingMachine" =>
        chiselMainTest(tutArgs, () => Module(new VendingMachine())){
          c => new VendingMachineTests(c)}
      case "Router" => 
        chiselMainTest(tutArgs, () => Module(new Router())){
          c => new RouterTests(c)}
      case "Echo" => 
        chiselMainTest(tutArgs, () => Module(new Echo())){
          c => new EchoTests(c, "../src/in.wav", "../emulator/out.wav")}
      case "Darken" => 
        chiselMainTest(tutArgs, () => Module(new Darken())){
          c => new DarkenTests(c, "../src/in.im24", "../emulator/out.im24")}
    }
  }
}

