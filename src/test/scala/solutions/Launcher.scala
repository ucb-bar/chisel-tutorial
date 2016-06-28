package solutions

import Chisel._
import Chisel.iotesters.{ Backend => TesterBackend, _ }
import utils.TutorialRunner

object Launcher {
  val solutions = Map(
    "Accummulator" -> { (backendName: String) =>
      runPeekPokeTester(() => new Accumulator(), backendName) {
        (c, b) => new AccumulatorTests(c, b)
      }
    },
    "LFSR16" -> { (backendName: String) =>
      runPeekPokeTester(() => new LFSR16(), backendName) {
        (c, b) => new LFSR16Tests(c, b)
      }
    },
    "SingleEvenFilter" -> { (backendName: String) =>
      runPeekPokeTester(() => new SingleEvenFilter(UInt(width = 16)), backendName) {
        (c, b) => new SingleEvenFilterTests(c, b)
      }
    },
    "VecShiftRegister" -> { (backendName: String) =>
      runPeekPokeTester(() => new VecShiftRegister(), backendName) {
        (c, b) => new VecShiftRegisterTests(c, b)
      }
    },
    "VecShiftRegisterSimple" -> { (backendName: String) =>
      runPeekPokeTester(() => new VecShiftRegisterSimple(), backendName) {
        (c, b) => new VecShiftRegisterSimpleTests(c, b)
      }
    },
    "VecShiftRegisterParam" -> { (backendName: String) =>
      runPeekPokeTester(() => new VecShiftRegisterParam(8, 4), backendName) {
        (c, b) => new VecShiftRegisterParamTests(c, b)
      }
    },
    "Max2" -> { (backendName: String) =>
      runPeekPokeTester(() => new Max2(), backendName) {
        (c, b) => new Max2Tests(c, b)
      }
    },
    "MaxN" -> { (backendName: String) =>
      runPeekPokeTester(() => new MaxN(8, 16), backendName) {
        (c, b) => new MaxNTests(c, b)
      }
    },
    "Adder" -> { (backendName: String) =>
      runPeekPokeTester(() => new Adder(8), backendName) {
        (c, b) => new AdderTests(c, b)
      }
    },
    "DynamicMemorySearch" -> { (backendName: String) =>
      runPeekPokeTester(() => new DynamicMemorySearch(8, 4), backendName) {
        (c, b) => new DynamicMemorySearchTests(c, b)
      }
    },
    "RealGCD" -> { (backendName: String) =>
      runPeekPokeTester(() => new RealGCD(), backendName) {
        (c, b) => new RealGCDTests(c, b)
      }
    },
    "Mux2" -> { (backendName: String) =>
      runPeekPokeTester(() => new Mux2(), backendName) {
        (c, b) => new Mux2Tests(c, b)
      }
    },
    "Mux4" -> { (backendName: String) =>
      runPeekPokeTester(() => new Mux4(), backendName) {
        (c, b) => new Mux4Tests(c, b)
      }
    },
    "Memo" -> { (backendName: String) =>
      runPeekPokeTester(() => new Memo(), backendName) {
        (c, b) => new MemoTests(c, b)
      }
    },
    "Mul" -> { (backendName: String) =>
      runPeekPokeTester(() => new Mul(), backendName) {
        (c, b) => new MulTests(c, b)
      }
    },
    "Counter" -> { (backendName: String) =>
      runPeekPokeTester(() => new Counter(), backendName) {
        (c, b) => new CounterTest(c, b)
      }
    },
    "VendingMachine" -> { (backendName: String) =>
      runPeekPokeTester(() => new VendingMachine(), backendName) {
        (c, b) => new VendingMachineTests(c, b)
      }
    }
  )

  def main(args: Array[String]): Unit = {
    TutorialRunner(solutions, args)
  }
}


