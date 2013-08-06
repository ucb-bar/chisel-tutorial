package TutorialSolutions

import Chisel._

object TutorialSolutions {
  def main(args: Array[String]): Unit = { 
    val tutArgs = args.slice(1, args.length) 
    val res = 
    args(0) match {
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
      case "Filter" => 
        chiselMainTest(tutArgs, () => Module(new Filter())){
          c => new FilterTests(c)}
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

