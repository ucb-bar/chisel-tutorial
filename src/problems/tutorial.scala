package Tutorial

import Chisel._

object Tutorial {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length) 
    val res = 
    args(0) match {
      case "GCD" => 
        chiselMainTest(tutArgs, () => new GCD()){
          c => new GCDTests(c)}
      case "RealGCD" => 
        chiselMainTest(tutArgs, () => new RealGCD()){
          c => new RealGCDTests(c)}
      case "Combinational" => 
        chiselMainTest(tutArgs, () => new Combinational()){
          c => new CombinationalTests(c)}
      case "Functional" => 
        chiselMainTest(tutArgs, () => new Functional()){
          c => new FunctionalTests(c)}
      case "Mux2" => 
        chiselMainTest(tutArgs, () => new Mux2()){
          c => new Mux2Tests(c)}
      case "Mux4" =>
        chiselMainTest(tutArgs, () => new Mux4()){
          c => new Mux4Tests(c)}
      case "Accumulator" => 
        chiselMainTest(tutArgs, () => new Accumulator()){
          c => new AccumulatorTests(c)}
      case "Parity" => 
        chiselMainTest(tutArgs, () => new Parity()){
          c => new ParityTests(c)}
      case "Memo" => 
        chiselMainTest(tutArgs, () => new Memo()){
          c => new MemoTests(c)}
      case "Filter" => 
        chiselMainTest(tutArgs, () => new Filter()){
          c => new FilterTests(c)}
      case "Tbl" => 
        chiselMainTest(tutArgs, () => new Tbl()){
          c => new TblTests(c)}
      case "Life" => 
        chiselMainTest(tutArgs, () => new Life(3)){
          c => new LifeTests(c)}
      case "Mul" => 
        chiselMainTest(tutArgs, () => new Mul()){
          c => new MulTests(c)}
      case "Risc" => 
        chiselMainTest(tutArgs, () => new Risc()){
          c => new RiscTests(c)}
      case "Counter" =>
        chiselMainTest(tutArgs, () => new Counter()){
          c => new CounterTest(c)}
      case "VendingMachine" =>
        chiselMainTest(tutArgs, () => new VendingMachine()){
          c => new VendingMachineTests(c)}
      case "Router" => 
        chiselMainTest(tutArgs, () => new Router()){
          c => new RouterTests(c)}
      case "Echo" => 
        chiselMainTest(tutArgs, () => new Echo()){
          c => new EchoTests(c, "../src/in.wav", "../emulator/out.wav")}
      case "Darken" => 
        chiselMainTest(tutArgs, () => new Darken()){
          c => new DarkenTests(c, "../src/in.im24", "../src/out.im24")}
    }
  }
}

