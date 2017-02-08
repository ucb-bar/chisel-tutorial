// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.iotesters.Driver
import utils.TutorialRunner

object Launcher {
  val tests = Map(
    "Accumulator" -> { (backendName: String) =>
      Driver(() => new Accumulator(), backendName) {
        (c) => new AccumulatorTests(c)
      }
    },
    "LFSR16" -> { (backendName: String) =>
      Driver(() => new LFSR16(), backendName) {
        (c) => new LFSR16Tests(c)
      }
    },
    "SingleEvenFilter" -> { (backendName: String) =>
      Driver(() => new SingleEvenFilter(UInt(16.W)), backendName) {
        (c) => new SingleEvenFilterTests(c)
      }
    },
    "VecShiftRegister" -> { (backendName: String) =>
      Driver(() => new VecShiftRegister(), backendName) {
        (c) => new VecShiftRegisterTests(c)
      }
    },
    "VecShiftRegisterSimple" -> { (backendName: String) =>
      Driver(() => new VecShiftRegisterSimple(), backendName) {
        (c) => new VecShiftRegisterSimpleTests(c)
      }
    },
    "VecShiftRegisterParam" -> { (backendName: String) =>
      Driver(() => new VecShiftRegisterParam(8, 4), backendName) {
        (c) => new VecShiftRegisterParamTests(c)
      }
    },
    "Max2" -> { (backendName: String) =>
      Driver(() => new Max2(), backendName) {
        (c) => new Max2Tests(c)
      }
    },
    "MaxN" -> { (backendName: String) =>
      Driver(() => new MaxN(8, 16), backendName) {
        (c) => new MaxNTests(c)
      }
    },
    "Adder" -> { (backendName: String) =>
      Driver(() => new Adder(8), backendName) {
        (c) => new AdderTests(c)
      }
    },
    "DynamicMemorySearch" -> { (backendName: String) =>
      Driver(() => new DynamicMemorySearch(8, 4), backendName) {
        (c) => new DynamicMemorySearchTests(c)
      }
    },
    "RealGCD" -> { (backendName: String) =>
      Driver(() => new RealGCD(), backendName) {
        (c) => new RealGCDTests(c)
      }
    },
    "Mux2" -> { (backendName: String) =>
      Driver(() => new Mux2(), backendName) {
        (c) => new Mux2Tests(c)
      }
    },
    "Mux4" -> { (backendName: String) =>
      Driver(() => new Mux4(), backendName) {
        (c) => new Mux4Tests(c)
      }
    },
    "Memo" -> { (backendName: String) =>
      Driver(() => new Memo(), backendName) {
        (c) => new MemoTests(c)
      }
    },
    "Mul" -> { (backendName: String) =>
      Driver(() => new Mul(), backendName) {
        (c) => new MulTests(c)
      }
    },
    "Counter" -> { (backendName: String) =>
      Driver(() => new Counter(), backendName) {
        (c) => new CounterTest(c)
      }
    },
    "VendingMachine" -> { (backendName: String) =>
      Driver(() => new VendingMachine(), backendName) {
        (c) => new VendingMachineTests(c)
      }
    },
    "VendingMachineSwitch" -> { (backendName: String) =>
      Driver(() => new VendingMachineSwitch(), backendName) {
        (c) => new VendingMachineSwitchTests(c)
      }
    }

  )

  def main(args: Array[String]): Unit = {
    TutorialRunner(tests, args)
  }
}
