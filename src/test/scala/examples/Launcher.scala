// See LICENSE.txt for license details.
package examples

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "Combinational" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Combinational()) {
          (c) => new CombinationalTests(c)
        }
      },
      "Functionality" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Functionality()) {
          (c) => new FunctionalityTests(c)
        }
      },
      "Parity" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Parity()) {
          (c) => new ParityTests(c)
        }
      },
      "Tbl" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Tbl()) {
          (c) => new TblTests(c)
        }
      },
      "Life" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Life(12)) {
          (c) => new LifeTests(c)
        }
      },
      "Risc" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Risc()) {
          (c) => new RiscTests(c)
        }
      },
      "Darken" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Darken()) {
          (c) => new DarkenTests(c, getClass.getResourceAsStream("/in.im24"), "o" + "u,t.im24")
        }
      },
      "Adder" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Adder(8)) {
          (c) => new AdderTests(c)
        }
      },
      "Adder4" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Adder4()) {
          (c) => new Adder4Tests(c)
        }
      },
      "SimpleALU" -> { (args: Array[String]) =>
        Driver.execute(args, () => new SimpleALU()) {
          (c) => new SimpleALUTests(c)
        }
      },
      "FullAdder" -> { (args: Array[String]) =>
        Driver.execute(args, () => new FullAdder()) {
          (c) => new FullAdderTests(c)
        }
      },
    "ByteSelector" -> { (args: Array[String]) =>
      Driver.execute(args, () => new ByteSelector()) {
        (c) => new ByteSelectorTests(c)
      }
    },
    "GCD" -> { (args: Array[String]) =>
      Driver.execute(args, () => new GCD) {
        (c) => new GCDTests(c)
      }
    },
      "HiLoMultiplier" -> { (args: Array[String]) =>
        Driver.execute(args, () => new HiLoMultiplier()) {
          (c) => new HiLoMultiplierTests(c)
        }
      },
      "ShiftRegister" -> { (args: Array[String]) =>
        Driver.execute(args, () => new ShiftRegister()) {
          (c) => new ShiftRegisterTests(c)
        }
      },
      "ResetShiftRegister" -> { (args: Array[String]) =>
        Driver.execute(args, () => new ResetShiftRegister()) {
          (c) => new ResetShiftRegisterTests(c)
        }
      },
      "EnableShiftRegister" -> { (args: Array[String]) =>
        Driver.execute(args, () => new EnableShiftRegister()) {
          (c) => new EnableShiftRegisterTests(c)
        }
      },
      "LogShifter" -> { (args: Array[String]) =>
        Driver.execute(args, () => new LogShifter()) {
          (c) => new LogShifterTests(c)
        }
      },
      "VecSearch" -> { (args: Array[String]) =>
        Driver.execute(args, () => new VecSearch()) {
          (c) => new VecSearchTests(c)
        }
      },
      "Stack" -> { (args: Array[String]) =>
        Driver.execute(args, () => new Stack(8)) {
          (c) => new StackTests(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner(examples, args)
  }
}

