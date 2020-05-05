// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "Combinational" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Combinational(), manager) {
          (c) => new CombinationalTests(c)
        }
      },
      "Functionality" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Functionality(), manager) {
          (c) => new FunctionalityTests(c)
        }
      },
      "Parity" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Parity(), manager) {
          (c) => new ParityTests(c)
        }
      },
      "Tbl" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Tbl(), manager) {
          (c) => new TblTests(c)
        }
      },
      "Life" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Life(12, 12), manager) {
          (c) => new LifeTests(c)
        }
      },
      "Risc" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Risc(), manager) {
          (c) => new RiscTests(c)
        }
      },
      "Darken" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Darken(), manager) {
          (c) => new DarkenTests(c, getClass.getResourceAsStream("/in.im24"), "o" + "u,t.im24")
        }
      },
      "Adder" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Adder(8), manager) {
          (c) => new AdderTests(c)
        }
      },
      "Adder4" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Adder4(), manager) {
          (c) => new Adder4Tests(c)
        }
      },
      "SimpleALU" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new SimpleALU(), manager) {
          (c) => new SimpleALUTests(c)
        }
      },
      "FullAdder" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new FullAdder(), manager) {
          (c) => new FullAdderTests(c)
        }
      },
      "ByteSelector" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ByteSelector(), manager) {
          (c) => new ByteSelectorTests(c)
        }
      },
      "GCD" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new GCD, manager) {
          (c) => new GCDTests(c)
        }
      },
      "HiLoMultiplier" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new HiLoMultiplier(), manager) {
          (c) => new HiLoMultiplierTests(c)
        }
      },
      "ShiftRegister" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ShiftRegister(), manager) {
          (c) => new ShiftRegisterTests(c)
        }
      },
      "ResetShiftRegister" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ResetShiftRegister(), manager) {
          (c) => new ResetShiftRegisterTests(c)
        }
      },
      "EnableShiftRegister" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new EnableShiftRegister(), manager) {
          (c) => new EnableShiftRegisterTests(c)
        }
      },
      "LogShifter" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new LogShifter(), manager) {
          (c) => new LogShifterTests(c)
        }
      },
      "VecSearch" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new VecSearch(), manager) {
          (c) => new VecSearchTests(c)
        }
      },
      "Stack" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Stack(8), manager) {
          (c) => new StackTests(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}

