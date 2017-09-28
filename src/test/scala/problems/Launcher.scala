// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val tests = Map(
    "Accumulator" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Accumulator(), manager) {
        (c) => new AccumulatorTests(c)
      }
    },
    "LFSR16" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new LFSR16(), manager) {
        (c) => new LFSR16Tests(c)
      }
    },
    "SingleEvenFilter" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new SingleEvenFilter(UInt(16.W)), manager) {
        (c) => new SingleEvenFilterTests(c)
      }
    },
    "VecShiftRegister" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new VecShiftRegister(), manager) {
        (c) => new VecShiftRegisterTests(c)
      }
    },
    "VecShiftRegisterSimple" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new VecShiftRegisterSimple(), manager) {
        (c) => new VecShiftRegisterSimpleTests(c)
      }
    },
    "VecShiftRegisterParam" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new VecShiftRegisterParam(8, 4), manager) {
        (c) => new VecShiftRegisterParamTests(c)
      }
    },
    "Max2" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Max2(), manager) {
        (c) => new Max2Tests(c)
      }
    },
    "MaxN" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new MaxN(8, 16), manager) {
        (c) => new MaxNTests(c)
      }
    },
    "Adder" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Adder(8), manager) {
        (c) => new AdderTests(c)
      }
    },
    "DynamicMemorySearch" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new DynamicMemorySearch(8, 4), manager) {
        (c) => new DynamicMemorySearchTests(c)
      }
    },
    "RealGCD" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new RealGCD(), manager) {
        (c) => new RealGCDTests(c)
      }
    },
    "Mux2" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Mux2(), manager) {
        (c) => new Mux2Tests(c)
      }
    },
    "Mux4" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Mux4(), manager) {
        (c) => new Mux4Tests(c)
      }
    },
    "Memo" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Memo(), manager) {
        (c) => new MemoTests(c)
      }
    },
    "Mul" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Mul(), manager) {
        (c) => new MulTests(c)
      }
    },
    "Counter" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Counter(), manager) {
        (c) => new CounterTest(c)
      }
    },
    "VendingMachine" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new VendingMachine(), manager) {
        (c) => new VendingMachineTests(c)
      }
    },
    "VendingMachineSwitch" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new VendingMachineSwitch(), manager) {
        (c) => new VendingMachineSwitchTests(c)
      }
    }

  )

  def main(args: Array[String]): Unit = {
    TutorialRunner("problems", tests, args)
  }
}
