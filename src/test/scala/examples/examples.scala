package examples

import Chisel.iotesters._
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.HashMap

object Examples {
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
          (c,b) => new GCDTests(c,b)}
      case "Combinational" =>
        runPeekPokeTester(() => new Combinational()){
          (c,b) => new CombinationalTests(c,b)}
      case "Functionality" =>
        runPeekPokeTester(() => new Functionality()){
          (c,b) => new FunctionalityTests(c,b)}
      case "Parity" =>
        runPeekPokeTester(() => new Parity()){
          (c,b) => new ParityTests(c,b)}
      case "Tbl" =>
        runPeekPokeTester(() => new Tbl()){
          (c,b) => new TblTests(c,b)}
      case "Life" =>
        runPeekPokeTester(() => new Life(3)){
          (c,b) => new LifeTests(c,b)}
      case "Risc" =>
        runPeekPokeTester(() => new Risc()){
          (c,b) => new RiscTests(c,b)}
      case "Darken" =>
        runPeekPokeTester(() => new Darken()){
          (c,b) => new DarkenTests(c, "../src/in.im24", "out.im24", b)}
      case "Adder" =>
        runPeekPokeTester(() => new Adder(8)){
          (c,b) => new AdderTests(c,b)}
      case "Adder4" =>
        runPeekPokeTester(() => new Adder4()){
          (c,b) => new Adder4Tests(c,b)}
      case "SimpleALU" =>
        runPeekPokeTester(() => new SimpleALU()){
          (c,b) => new SimpleALUTests(c,b)}
      case "FullAdder" =>
        runPeekPokeTester(() => new FullAdder()){
          (c,b) => new FullAdderTests(c,b)}
      case "ByteSelector" =>
        runPeekPokeTester(() => new ByteSelector()){
          (c,b) => new ByteSelectorTests(c,b)}
      case "HiLoMultiplier" =>
        runPeekPokeTester(() => new HiLoMultiplier()){
          (c,b) => new HiLoMultiplierTests(c,b)}
      case "ShiftRegister" =>
        runPeekPokeTester(() => new ShiftRegister()){
          (c,b) => new ShiftRegisterTests(c,b)}
      case "ResetShiftRegister" =>
        runPeekPokeTester(() => new ResetShiftRegister()){
          (c,b) => new ResetShiftRegisterTests(c,b)}
      case "EnableShiftRegister" =>
        runPeekPokeTester(() => new EnableShiftRegister()){
          (c,b) => new EnableShiftRegisterTests(c,b)}
      case "LogShifter" =>
        runPeekPokeTester(() => new LogShifter()){
          (c,b) => new LogShifterTests(c,b)}
      case "VecSearch" =>
        runPeekPokeTester(() => new VecSearch()){
          (c,b) => new VecSearchTests(c,b)}
      case "Stack" =>
        runPeekPokeTester(() => new Stack(8)) {
          (c,b) => new StackTests(c,b)}
    }
    if(!res) {
      System.exit(1)
    }
  }
}

