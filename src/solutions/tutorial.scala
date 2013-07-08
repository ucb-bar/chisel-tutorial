package TutorialSolutions

import Chisel._

object TutorialSolutions {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length) 
    val res = 
    args(0) match {
      case "GCD" => 
        chiselMainTest(tutArgs, () => Mod(new GCD())){
          c => new GCDTests(c)}
      case "RealGCD" => 
        chiselMainTest(tutArgs, () => Mod(new RealGCD())){
          c => new RealGCDTests(c)}
      case "Combinational" => 
        chiselMainTest(tutArgs, () => Mod(new Combinational())){
          c => new CombinationalTests(c)}
      case "Functional" => 
        chiselMainTest(tutArgs, () => Mod(new Functional())){
          c => new FunctionalTests(c)}
      case "Mux2" => 
        chiselMainTest(tutArgs, () => Mod(new Mux2())){
          c => new Mux2Tests(c)}
      case "Mux4" =>
        chiselMainTest(tutArgs, () => Mod(new Mux4())){
          c => new Mux4Tests(c)}
      case "Accumulator" => 
        chiselMainTest(tutArgs, () => Mod(new Accumulator())){
          c => new AccumulatorTests(c)}
      case "Parity" => 
        chiselMainTest(tutArgs, () => Mod(new Parity())){
          c => new ParityTests(c)}
      case "Memo" => 
        chiselMainTest(tutArgs, () => Mod(new Memo())){
          c => new MemoTests(c)}
      case "Filter" => 
        chiselMainTest(tutArgs, () => Mod(new Filter())){
          c => new FilterTests(c)}
      case "Tbl" => 
        chiselMainTest(tutArgs, () => Mod(new Tbl())){
          c => new TblTests(c)}
      case "Life" => 
        chiselMainTest(tutArgs, () => Mod(new Life(3))){
          c => new LifeTests(c)}
      case "Mul" => 
        chiselMainTest(tutArgs, () => Mod(new Mul())){
          c => new MulTests(c)}
      case "Risc" => 
        chiselMainTest(tutArgs, () => Mod(new Risc())){
          c => new RiscTests(c)}
      case "Counter" =>
        chiselMainTest(tutArgs, () => Mod(new Counter())){
          c => new CounterTest(c)}
      case "VendingMachine" =>
        chiselMainTest(tutArgs, () => Mod(new VendingMachine())){
          c => new VendingMachineTests(c)}
      case "Router" => 
        chiselMainTest(tutArgs, () => Mod(new Router())){
          c => new RouterTests(c)}
      case "Echo" => 
        chiselMainTest(tutArgs, () => Mod(new Echo())){
          c => new EchoTests(c, "../src/in.wav", "../emulator/out.wav")}
      case "Darken" => 
        chiselMainTest(tutArgs, () => Mod(new Darken())){
          c => new DarkenTests(c, "../src/in.im24", "../emulator/out.im24")}
    }
  }
}

