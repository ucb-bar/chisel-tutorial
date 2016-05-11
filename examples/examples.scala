package TutorialExamples

import Chisel._
import Chisel.iotesters._
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
        chiselMainTest(tutArgs, () => new GCD()){
          c => new GCDTests(c)}
      case "Combinational" =>
        chiselMainTest(tutArgs, () => new Combinational()){
          c => new CombinationalTests(c)}
      case "Functionality" =>
        chiselMainTest(tutArgs, () => new Functionality()){
          c => new FunctionalityTests(c)}
      case "Parity" =>
        chiselMainTest(tutArgs, () => new Parity()){
          c => new ParityTests(c)}
      case "Tbl" =>
        chiselMainTest(tutArgs, () => new Tbl()){
          c => new TblTests(c)}
      case "Life" =>
        chiselMainTest(tutArgs, () => new Life(3)){
          c => new LifeTests(c)}
      case "Risc" =>
        chiselMainTest(tutArgs, () => new Risc()){
          c => new RiscTests(c)}
      case "Router" =>
        chiselMainTest(tutArgs, () => new Router()){
          c => new RouterTests(c)}
      case "Darken" =>
        chiselMainTest(tutArgs, () => new Darken()){
          c => new DarkenTests(c, "../src/in.im24", "out.im24")}
          // Chisel2-3 compatibility since this "test" is silent.
          //val pfString = if (ChiselError.hasErrors) "FAILED" else "PASSED"
          //println(s"${pfString} -- ${args(0)}")
      case "Adder" =>
        chiselMainTest(tutArgs, () => new Adder(8)){
          c => new AdderTests(c)}
      case "Adder4" =>
        chiselMainTest(tutArgs, () => new Adder4()){
          c => new Adder4Tests(c)}
      case "SimpleALU" =>
        chiselMainTest(tutArgs, () => new SimpleALU()){
          c => new SimpleALUTests(c)}
      case "FullAdder" =>
        chiselMainTest(tutArgs, () => new FullAdder()){
          c => new FullAdderTests(c)}
      case "FullAdder2" =>
        chiselMainTest(tutArgs, () => new FullAdder2()){
          c => new FullAdder2Tests(c)}
      case "ByteSelector" =>
        chiselMainTest(tutArgs, () => new ByteSelector()){
          c => new ByteSelectorTests(c)}
      case "HiLoMultiplier" =>
        chiselMainTest(tutArgs, () => new HiLoMultiplier()){
          c => new HiLoMultiplierTests(c)}
      case "ShiftRegister" =>
        chiselMainTest(tutArgs, () => new ShiftRegister()){
          c => new ShiftRegisterTests(c)}
      case "ResetShiftRegister" =>
        chiselMainTest(tutArgs, () => new ResetShiftRegister()){
          c => new ResetShiftRegisterTests(c)}
      case "EnableShiftRegister" =>
        chiselMainTest(tutArgs, () => new EnableShiftRegister()){
          c => new EnableShiftRegisterTests(c)}
      case "LogShifter" =>
        chiselMainTest(tutArgs, () => new LogShifter()){
          c => new LogShifterTests(c)}
      case "VecSearch" =>
        chiselMainTest(tutArgs, () => new VecSearch()){
          c => new VecSearchTests(c)}
      case "Stack" =>
        chiselMainTest(tutArgs, () => new Stack(8)) {
          c => new StackTests(c)}
      /*comment out MemorySearch because Chisel3 ClassicTester doesn't support peek/poke on internal signals*/
      /*case "MemorySearch" =>
        chiselMainTest(tutArgs, () => new MemorySearch()){
          c => new MemorySearchTests(c)}*/
      /*comment out FIR for now due to lack of Flo support*/
      /*case "FIR" =>
      chiselMainTest(tutArgs, () => new FIR()){
        c => new FIRTests(c)*/
    }
  }
}

