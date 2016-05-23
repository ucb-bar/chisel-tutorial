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
        runClassicTester(() => new GCD()){
          (c,p) => new GCDTests(c,p)}
      case "Combinational" =>
        runClassicTester(() => new Combinational()){
          (c,p) => new CombinationalTests(c,p)}
      case "Functionality" =>
        runClassicTester(() => new Functionality()){
          (c,p) => new FunctionalityTests(c,p)}
      case "Parity" =>
        runClassicTester(() => new Parity()){
          (c,p) => new ParityTests(c,p)}
      case "Tbl" =>
        runClassicTester(() => new Tbl()){
          (c,p) => new TblTests(c,p)}
      case "Life" =>
        runClassicTester(() => new Life(3)){
          (c,p) => new LifeTests(c,p)}
      case "Risc" =>
        runClassicTester(() => new Risc()){
          (c,p) => new RiscTests(c,p)}
      /* comment out Router for now due to unresolved Chisel3 compatibility issue */
      /*case "Router" =>
        runClassicTester(() => new Router()){
          (c,p) => new RouterTests(c,p)}*/
      case "Darken" =>
        runClassicTester(() => new Darken()){
          (c,p) => new DarkenTests(c, "../src/in.im24", "out.im24")}
          // Chisel2-3 compatibility since this "test" is silent.
          //val pfString = if (ChiselError.hasErrors) "FAILED" else "PASSED"
          //println(s"${pfString} -- ${args(0)}")
      case "Adder" =>
        runClassicTester(() => new Adder(8)){
          (c,p) => new AdderTests(c,p)}
      case "Adder4" =>
        runClassicTester(() => new Adder4()){
          (c,p) => new Adder4Tests(c,p)}
      case "SimpleALU" =>
        runClassicTester(() => new SimpleALU()){
          (c,p) => new SimpleALUTests(c,p)}
      case "FullAdder" =>
        runClassicTester(() => new FullAdder()){
          (c,p) => new FullAdderTests(c,p)}
      case "FullAdder2" =>
        runClassicTester(() => new FullAdder2()){
          (c,p) => new FullAdder2Tests(c,p)}
      case "ByteSelector" =>
        runClassicTester(() => new ByteSelector()){
          (c,p) => new ByteSelectorTests(c,p)}
      case "HiLoMultiplier" =>
        runClassicTester(() => new HiLoMultiplier()){
          (c,p) => new HiLoMultiplierTests(c,p)}
      case "ShiftRegister" =>
        runClassicTester(() => new ShiftRegister()){
          (c,p) => new ShiftRegisterTests(c,p)}
      case "ResetShiftRegister" =>
        runClassicTester(() => new ResetShiftRegister()){
          (c,p) => new ResetShiftRegisterTests(c,p)}
      case "EnableShiftRegister" =>
        runClassicTester(() => new EnableShiftRegister()){
          (c,p) => new EnableShiftRegisterTests(c,p)}
      case "LogShifter" =>
        runClassicTester(() => new LogShifter()){
          (c,p) => new LogShifterTests(c,p)}
      case "VecSearch" =>
        runClassicTester(() => new VecSearch()){
          (c,p) => new VecSearchTests(c,p)}
      case "Stack" =>
        runClassicTester(() => new Stack(8)) {
          (c,p) => new StackTests(c,p)}
      /*comment out MemorySearch because Chisel3 ClassicTester doesn't support peek/poke on internal signals*/
      /*case "MemorySearch" =>
        runClassicTester(() => new MemorySearch()){
          (c,p) => new MemorySearchTests(c,p)}*/
      /*comment out FIR for now due to lack of Flo support*/
      /*case "FIR" =>
      runClassicTester(() => new FIR()){
        (c,p) => new FIRTests(c,p)*/
    }
  }
}

