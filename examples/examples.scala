package TutorialExamples

import Chisel._

object TutorialExamples {
  def main(args: Array[String]): Unit = {
    val tutArgs = args.slice(1, args.length)
    val res =
    args(0) match {
      case "GCD" =>
        chiselMainTest(tutArgs, () => Module(new GCD())){
          c => new GCDTests(c)}
      case "Combinational" =>
        chiselMainTest(tutArgs, () => Module(new Combinational())){
          c => new CombinationalTests(c)}
      case "Functional" =>
        chiselMainTest(tutArgs, () => Module(new Functional())){
          c => new FunctionalTests(c)}
      case "Accumulator" =>
        chiselMainTest(tutArgs, () => Module(new Accumulator())){
          c => new AccumulatorTests(c)}
      case "Parity" =>
        chiselMainTest(tutArgs, () => Module(new Parity())){
          c => new ParityTests(c)}
      case "Tbl" =>
        chiselMainTest(tutArgs, () => Module(new Tbl())){
          c => new TblTests(c)}
      case "Life" =>
        chiselMainTest(tutArgs, () => Module(new Life(3))){
          c => new LifeTests(c)}
      case "Risc" =>
        chiselMainTest(tutArgs, () => Module(new Risc())){
          c => new RiscTests(c)}
      case "Router" =>
        chiselMainTest(tutArgs, () => Module(new Router())){
          c => new RouterTests(c)}
      case "Echo" =>
        chiselMainTest(tutArgs, () => Module(new Echo())){
          c => new EchoTests(c, "../src/in.wav", "out.wav")}
      case "Darken" =>
        chiselMainTest(tutArgs, () => Module(new Darken())){
          c => new DarkenTests(c, "../src/in.im24", "out.im24")}
    }
  }
}

