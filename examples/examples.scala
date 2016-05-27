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
        runPeekPokeTester(() => new GCD()){
          (c,p) => new GCDTests(c,p)}
      case "Combinational" =>
        runPeekPokeTester(() => new Combinational()){
          (c,p) => new CombinationalTests(c,p)}
      case "Functionality" =>
        runPeekPokeTester(() => new Functionality()){
          (c,p) => new FunctionalityTests(c,p)}
      case "Parity" =>
        runPeekPokeTester(() => new Parity()){
          (c,p) => new ParityTests(c,p)}
      case "Tbl" =>
        runPeekPokeTester(() => new Tbl()){
          (c,p) => new TblTests(c,p)}
      case "Life" =>
        runPeekPokeTester(() => new Life(3)){
          (c,p) => new LifeTests(c,p)}
      case "Risc" =>
        runPeekPokeTester(() => new Risc()){
          (c,p) => new RiscTests(c,p)}
      case "Darken" =>
        runPeekPokeTester(() => new Darken()){
          (c,p) => new DarkenTests(c, "../src/in.im24", "out.im24", p)}
      case "Adder" =>
        runPeekPokeTester(() => new Adder(8)){
          (c,p) => new AdderTests(c,p)}
      case "Adder4" =>
        runPeekPokeTester(() => new Adder4()){
          (c,p) => new Adder4Tests(c,p)}
      case "SimpleALU" =>
        runPeekPokeTester(() => new SimpleALU()){
          (c,p) => new SimpleALUTests(c,p)}
      case "FullAdder" =>
        runPeekPokeTester(() => new FullAdder()){
          (c,p) => new FullAdderTests(c,p)}
      case "FullAdder2" =>
        runPeekPokeTester(() => new FullAdder2()){
          (c,p) => new FullAdder2Tests(c,p)}
      case "ByteSelector" =>
        runPeekPokeTester(() => new ByteSelector()){
          (c,p) => new ByteSelectorTests(c,p)}
      case "HiLoMultiplier" =>
        runPeekPokeTester(() => new HiLoMultiplier()){
          (c,p) => new HiLoMultiplierTests(c,p)}
      case "ShiftRegister" =>
        runPeekPokeTester(() => new ShiftRegister()){
          (c,p) => new ShiftRegisterTests(c,p)}
      case "ResetShiftRegister" =>
        runPeekPokeTester(() => new ResetShiftRegister()){
          (c,p) => new ResetShiftRegisterTests(c,p)}
      case "EnableShiftRegister" =>
        runPeekPokeTester(() => new EnableShiftRegister()){
          (c,p) => new EnableShiftRegisterTests(c,p)}
      case "LogShifter" =>
        runPeekPokeTester(() => new LogShifter()){
          (c,p) => new LogShifterTests(c,p)}
      case "VecSearch" =>
        runPeekPokeTester(() => new VecSearch()){
          (c,p) => new VecSearchTests(c,p)}
      case "Stack" =>
        runPeekPokeTester(() => new Stack(8)) {
          (c,p) => new StackTests(c,p)}
    }
  }
}

