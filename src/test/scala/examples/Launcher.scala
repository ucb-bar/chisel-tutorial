// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "Combinational" -> { (backendName: String) =>
        Driver(() => new Combinational(), backendName) {
          (c) => new CombinationalTests(c)
        }
      },
      "Functionality" -> { (backendName: String) =>
        Driver(() => new Functionality(), backendName) {
          (c) => new FunctionalityTests(c)
        }
      },
      "Parity" -> { (backendName: String) =>
        Driver(() => new Parity(), backendName) {
          (c) => new ParityTests(c)
        }
      },
      "Tbl" -> { (backendName: String) =>
        Driver(() => new Tbl(), backendName) {
          (c) => new TblTests(c)
        }
      },
      "Life" -> { (backendName: String) =>
        Driver(() => new Life(3), backendName) {
          (c) => new LifeTests(c)
        }
      },
      "Risc" -> { (backendName: String) =>
        Driver(() => new Risc(), backendName) {
          (c) => new RiscTests(c)
        }
      },
      "Darken" -> { (backendName: String) =>
        Driver(() => new Darken(), backendName) {
          (c) => new DarkenTests(c, getClass.getResourceAsStream("/in.im24"), "o" + "u,t.im24")
        }
      },
      "Adder" -> { (backendName: String) =>
        Driver(() => new Adder(8), backendName) {
          (c) => new AdderTests(c)
        }
      },
      "Adder4" -> { (backendName: String) =>
        Driver(() => new Adder4(), backendName) {
          (c) => new Adder4Tests(c)
        }
      },
      "SimpleALU" -> { (backendName: String) =>
        Driver(() => new SimpleALU(), backendName) {
          (c) => new SimpleALUTests(c)
        }
      },
      "FullAdder" -> { (backendName: String) =>
        Driver(() => new FullAdder(), backendName) {
          (c) => new FullAdderTests(c)
        }
      },
      "ByteSelector" -> { (backendName: String) =>
        Driver(() => new ByteSelector(), backendName) {
          (c) => new ByteSelectorTests(c)
        }
      },
      "HiLoMultiplier" -> { (backendName: String) =>
        Driver(() => new HiLoMultiplier(), backendName) {
          (c) => new HiLoMultiplierTests(c)
        }
      },
      "ShiftRegister" -> { (backendName: String) =>
        Driver(() => new ShiftRegister(), backendName) {
          (c) => new ShiftRegisterTests(c)
        }
      },
      "ResetShiftRegister" -> { (backendName: String) =>
        Driver(() => new ResetShiftRegister(), backendName) {
          (c) => new ResetShiftRegisterTests(c)
        }
      },
      "EnableShiftRegister" -> { (backendName: String) =>
        Driver(() => new EnableShiftRegister(), backendName) {
          (c) => new EnableShiftRegisterTests(c)
        }
      },
      "LogShifter" -> { (backendName: String) =>
        Driver(() => new LogShifter(), backendName) {
          (c) => new LogShifterTests(c)
        }
      },
      "VecSearch" -> { (backendName: String) =>
        Driver(() => new VecSearch(), backendName) {
          (c) => new VecSearchTests(c)
        }
      },
      "Stack" -> { (backendName: String) =>
        Driver(() => new Stack(8), backendName) {
          (c) => new StackTests(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner(examples, args)
  }
}

