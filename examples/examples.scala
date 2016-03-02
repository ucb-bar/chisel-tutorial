package TutorialExamples

import Chisel._
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.HashMap

object TutorialExamples {
  def filterArgs(args: Array[String], amap: HashMap[String, String]): Array[String] = {
    val newArgs = ArrayBuffer[String]()
    for (arg <- args) {
      if (amap.contains(arg)) {
        newArgs += amap(arg)
      } else {
        newArgs += arg
      }
    }
    newArgs.toArray
  }

  def main(args: Array[String]): Unit = {
    val tutArgs = args.slice(1, args.length)
    val res =
    args(0) match {
      case "GCD" =>
        chiselMainTest(tutArgs, () => Module(new GCD())){
          c => new GCDTester(c)}
      case "Combinational" =>
        chiselMainTest(tutArgs, () => Module(new Combinational())){
          c => new CombinationalTests(c)}
      case "Functionality" =>
        chiselMainTest(tutArgs, () => Module(new Functionality())){
          c => new FunctionalityTests(c)}
      case "Parity" =>
        chiselMainTest(tutArgs, () => Module(new Parity())){
          c => new ParityTester(c)}
      case "Tbl" =>
        chiselMainTest(tutArgs, () => Module(new Tbl())){
          c => new TblTests(c)}
      case "Life" =>
        chiselMainTest(tutArgs, () => Module(new Life(3))){
          c => new LifeTests(c)}
      case "Risc" =>
        chiselMainTest(tutArgs, () => Module(new Risc())){
          c => new RiscTester(c)}
      case "Router" =>
        chiselMainTest(tutArgs, () => Module(new Router())){
          c => new RouterTester(c)}
      case "Darken" =>
        chiselMainTest(tutArgs, () => Module(new Darken())){
          c => new DarkenTests(c, "../src/in.im24", "out.im24")}
      case "Adder" =>
        chiselMainTest(tutArgs, () => Module(new Adder(8))){
          c => new AdderTests(c)}
      case "Adder4" =>
        chiselMainTest(tutArgs, () => Module(new Adder4())){
          c => new Adder4Tests(c)}
      case "SimpleALU" =>
        chiselMainTest(tutArgs, () => Module(new SimpleALU())){
          c => new SimpleALUTests(c)}
      case "FullAdder" =>
        chiselMainTest(tutArgs, () => Module(new FullAdder())){
          c => new FullAdderTests(c)}
      case "FullAdder2" =>
        chiselMainTest(tutArgs, () => Module(new FullAdder2())){
          c => new FullAdder2Tests(c)}
      case "ByteSelector" =>
        chiselMainTest(tutArgs, () => Module(new ByteSelector())){
          c => new ByteSelectorTests(c)}
      case "HiLoMultiplier" =>
        chiselMainTest(tutArgs, () => Module(new HiLoMultiplier())){
          c => new HiLoMultiplierTests(c)}
      case "ShiftRegister" =>
        chiselMainTest(tutArgs, () => Module(new ShiftRegister())){
          c => new ShiftRegisterTester(c)}
      case "ResetShiftRegister" =>
        chiselMainTest(tutArgs, () => Module(new ResetShiftRegister())){
          c => new ResetShiftRegisterTester(c)}
      case "EnableShiftRegister" =>
        chiselMainTest(tutArgs, () => Module(new EnableShiftRegister())){
          c => new EnableShiftRegisterTester(c)}
      case "LogShifter" =>
        chiselMainTest(tutArgs, () => Module(new LogShifter())){
          c => new LogShifterTests(c)}
      case "VecSearch" =>
        chiselMainTest(tutArgs, () => Module(new VecSearch())){
          c => new VecSearchTests(c)}
      case "MemorySearch" =>
        chiselMainTest(tutArgs, () => Module(new MemorySearch())){
          c => new MemorySearchTester(c)}
      case "Stack" =>
        chiselMainTest(tutArgs, () => Module(new Stack(8))){
          c => new StackTester(c)}
      case "FIR" =>
        chiselMainTest(tutArgs, () => Module(new FIR())){
          c => new FIRTests(c)}
    }
  }
}

