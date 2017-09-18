// See LICENSE.txt for license details.
package solutions

import chisel3._
import chisel3.iotesters.Driver
import utils.TutorialRunner

object Launcher {
  val tests = Map(
    "Accumulator" -> { (args: Array[String]) =>
      Driver.execute(args, () => new Accumulator()) {
        (c) => new AccumulatorTests(c)
      }
    },
    "LFSR16" -> { (args: Array[String]) =>
      Driver.execute(args, () => new LFSR16()) {
        (c) => new LFSR16Tests(c)
      }
    },
    "SingleEvenFilter" -> { (args: Array[String]) =>
      Driver.execute(args, () => new SingleEvenFilter(UInt(16.W))) {
        (c) => new SingleEvenFilterTests(c)
      }
    },
    "VecShiftRegister" -> { (args: Array[String]) =>
      Driver.execute(args, () => new VecShiftRegister()) {
        (c) => new VecShiftRegisterTests(c)
      }
    },
    "VecShiftRegisterSimple" -> { (args: Array[String]) =>
      Driver.execute(args, () => new VecShiftRegisterSimple()) {
        (c) => new VecShiftRegisterSimpleTests(c)
      }
    },
    "VecShiftRegisterParam" -> { (args: Array[String]) =>
      Driver.execute(args, () => new VecShiftRegisterParam(8, 4)) {
        (c) => new VecShiftRegisterParamTests(c)
      }
    },
    "Max2" -> { (args: Array[String]) =>
      Driver.execute(args, () => new Max2()) {
        (c) => new Max2Tests(c)
      }
    },
    "MaxN" -> { (args: Array[String]) =>
      Driver.execute(args, () => new MaxN(8, 16)) {
        (c) => new MaxNTests(c)
      }
    },
    "Adder" -> { (args: Array[String]) =>
      Driver.execute(args, () => new Adder(8)) {
        (c) => new AdderTests(c)
      }
    },
    "DynamicMemorySearch" -> { (args: Array[String]) =>
      Driver.execute(args, () => new DynamicMemorySearch(8, 4)) {
        (c) => new DynamicMemorySearchTests(c)
      }
    },
    "RealGCD" -> { (args: Array[String]) =>
      Driver.execute(args, () => new RealGCD()) {
        (c) => new RealGCDTests(c)
      }
    },
    "Mux4" -> { (args: Array[String]) =>
      Driver.execute(args, () => new Mux4()) {
        (c) => new Mux4Tests(c)
      }
    },
    "Memo" -> { (args: Array[String]) =>
      Driver.execute(args, () => new Memo()) {
        (c) => new MemoTests(c)
      }
    },
    "Mul" -> { (args: Array[String]) =>
      Driver.execute(args, () => new Mul()) {
        (c) => new MulTests(c)
      }
    },
    "Counter" -> { (args: Array[String]) =>
      Driver.execute(args, () => new Counter()) {
        (c) => new CounterTest(c)
      }
    },
    "VendingMachine" -> { (args: Array[String]) =>
      Driver.execute(args, () => new VendingMachine()) {
        (c) => new VendingMachineTests(c)
      }
    },
    "VendingMachineSwitch" -> { (args: Array[String]) =>
      Driver.execute(args, () => new VendingMachineSwitch()) {
        (c) => new VendingMachineSwitchTests(c)
      }
    }

  )

  def main(args: Array[String]): Unit = {
    TutorialRunner(tests, args)
  }
}
