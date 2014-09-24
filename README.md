Chisel Tutorials
================

These are the tutorials for [Chisel](https://github.com/ucb-bar/chisel).

Chisel is an open-source hardware construction language developed
at UC Berkeley that supports advanced hardware design using highly
parameterized generators and layered domain-specific hardware languages.

Visit the [community website](http://chisel.eecs.berkeley.edu/) for more
information.


Getting the Repo
----------------

    $ git clone https://github.com/ucb-bar/chisel-tutorial.git


Executing Chisel
----------------

####Testing Your System
First make sure the prerequisites are installed. These include make, gcc
and [sbt](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

    $ cd hello
    $ make

This will generate and test a simple block (`Hello`) that always outputs the
number 42. You should see `[success]` on the last line of output (from sbt) and
`PASSED` on the line before indicating the block passed the testcase. If you
are doing this for the first time, sbt will automatically download the
appropriate versions of Chisel and Scala and cache them (usually in `~/.ivy2`).


####Manual Execution
The make recipe above automically invoked sbt to use Chisel's C backend to
generate a C emulator and ran it against the testcase. To do it mannually:

    $ sbt "run Hello --backend c --compile --test --genHarness"

To generate a waveform (`Hello.vcd`) of the testcase execution:

    $ sbt "run Hello --backend c --compile --test --genHarness --vcd"

To generate Verilog instead of a C emulator:

    $ sbt "run Hello --backend v --genHarness"


Completing the Tutorials
------------------------

To learn Chisel, we recommend learning by example and just trying things out.
To help with this, we have produced exercises (`/problems`) which have clearly
marked places to complete their functionality and simple test cases. You can
compare your work with our sample solutions (`/solutions`).

To speed things up, we will keep sbt running. To get started:

    $ cd problems
    $ sbt

#### Mux2
This should already work. Try

    > run Mux2 --backend c --targetDir ../emulator --compile --test --genHarness

#### Mux4
You can instantiate a module with `val foo = Module(new Bar())`

    > run Mux4 --backend c --targetDir ../emulator --compile --test --genHarness

#### Counter
You can conditionally update a value without a mux by using `when (cond) { foo := bar }`

    > run Counter --backend c --targetDir ../emulator --compile --test --genHarness

#### Vending Machine

    > run VendingMachine --backend c --targetDir ../emulator --compile --test --genHarness`

#### Memo
The type of memory that's inferred is based on how you handle the read and
write enables. This is pretty much the same as how Xilinx and Altera infer
memories.

    > run Memo --backend c --targetDir ../emulator --compile --test --genHarness

#### Mul

    > run Mul --backend c --targetDir ../emulator --compile --test --genHarness

#### RealGCD

    > run RealGCD --backend c --targetDir ../emulator --compile --test --genHarness



Learning More Chisel
--------------------
In addition to the problems and the solutions, we have also provided some
examples of more complex circuits (`/examples`). You should take a look at the
source and test them out:

    $ cd examples
    $ make

On our [website](http://chisel.eecs.berkeley.edu/) we also have posted
[documentation](https://chisel.eecs.berkeley.edu/documentation.html).
