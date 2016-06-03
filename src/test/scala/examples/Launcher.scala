package examples

import Chisel.iotesters._
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "GCD" -> { (backendName: String) =>
        runPeekPokeTester(() => new GCD(), backendName) {
          (c, b) => new GCDTests(c, b)
        }
      },
      "Combinational" -> { (backendName: String) =>
        runPeekPokeTester(() => new Combinational(), backendName) {
          (c, b) => new CombinationalTests(c, b)
        }
      },
      "Functionality" -> { (backendName: String) =>
        runPeekPokeTester(() => new Functionality(), backendName) {
          (c, b) => new FunctionalityTests(c, b)
        }
      },
      "Parity" -> { (backendName: String) =>
        runPeekPokeTester(() => new Parity(), backendName) {
          (c, b) => new ParityTests(c, b)
        }
      },
      "Tbl" -> { (backendName: String) =>
        runPeekPokeTester(() => new Tbl(), backendName) {
          (c, b) => new TblTests(c, b)
        }
      },
      "Life" -> { (backendName: String) =>
        runPeekPokeTester(() => new Life(3), backendName) {
          (c, b) => new LifeTests(c, b)
        }
      },
      "Risc" -> { (backendName: String) =>
        runPeekPokeTester(() => new Risc(), backendName) {
          (c, b) => new RiscTests(c, b)
        }
      },
      "Darken" -> { (backendName: String) =>
        runPeekPokeTester(() => new Darken(), backendName) {
          (c, b) => new DarkenTests(c, "src/test/resources/in.im24", "o" +
            "u,t.im24", b)
        }
      },
      "Adder" -> { (backendName: String) =>
        runPeekPokeTester(() => new Adder(8), backendName) {
          (c, b) => new AdderTests(c, b)
        }
      },
      "Adder4" -> { (backendName: String) =>
        runPeekPokeTester(() => new Adder4(), backendName) {
          (c, b) => new Adder4Tests(c, b)
        }
      },
      "SimpleALU" -> { (backendName: String) =>
        runPeekPokeTester(() => new SimpleALU(), backendName) {
          (c, b) => new SimpleALUTests(c, b)
        }
      },
      "FullAdder" -> { (backendName: String) =>
        runPeekPokeTester(() => new FullAdder(), backendName) {
          (c, b) => new FullAdderTests(c, b)
        }
      },
      "ByteSelector" -> { (backendName: String) =>
        runPeekPokeTester(() => new ByteSelector(), backendName) {
          (c, b) => new ByteSelectorTests(c, b)
        }
      },
      "HiLoMultiplier" -> { (backendName: String) =>
        runPeekPokeTester(() => new HiLoMultiplier(), backendName) {
          (c, b) => new HiLoMultiplierTests(c, b)
        }
      },
      "ShiftRegister" -> { (backendName: String) =>
        runPeekPokeTester(() => new ShiftRegister(), backendName) {
          (c, b) => new ShiftRegisterTests(c, b)
        }
      },
      "ResetShiftRegister" -> { (backendName: String) =>
        runPeekPokeTester(() => new ResetShiftRegister(), backendName) {
          (c, b) => new ResetShiftRegisterTests(c, b)
        }
      },
      "EnableShiftRegister" -> { (backendName: String) =>
        runPeekPokeTester(() => new EnableShiftRegister(), backendName) {
          (c, b) => new EnableShiftRegisterTests(c, b)
        }
      },
      "LogShifter" -> { (backendName: String) =>
        runPeekPokeTester(() => new LogShifter(), backendName) {
          (c, b) => new LogShifterTests(c, b)
        }
      },
      "VecSearch" -> { (backendName: String) =>
        runPeekPokeTester(() => new VecSearch(), backendName) {
          (c, b) => new VecSearchTests(c, b)
        }
      },
      "Stack" -> { (backendName: String) =>
        runPeekPokeTester(() => new Stack(8), backendName) {
          (c, b) => new StackTests(c, b)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner(examples, args)
  }
}

