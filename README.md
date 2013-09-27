Chisel Tutorials
================

These are the tutorials for [Chisel](https://github.com/ucb-bar/chisel).

Chisel is an open-source hardware construction language developed
at UC Berkeley that supports advanced hardware design using highly
parameterized generators and layered domain-specific hardware languages.

Visit the [community website](http://chisel.eecs.berkeley.edu/) for more
information.

Getting the repo
================

    $ git clone https://github.com/ucb-bar/chisel-tutorial.git

Running the Tutorials
=====================

First make sure the prerequisites are installed. These include make, gcc
and [sbt](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

    # solutions to the partially implemented circuits in  problems/
    $ cd solutions
    $ make

    # more complex examples of circuits implemented in Chisel
    $ cd examples
    $ make


Doing the problems
=====================
`cd problems`

`sbt`

### Mux2
This should already work. Try
`run Mux2 --backend c --targetDir ../emulator --compile --test --genHarness`

### Mux4

You can instantiate a module with `val foo = Module(new Bar())`
`run Mux4 --backend c --targetDir ../emulator --compile --test --genHarness`

### Counter
You can conditionally update a value without a mux by using `when (cond) { foo := bar }`
`run Counter --backend c --targetDir ../emulator --compile --test --genHarness`

### Vending Machine
`run VendingMachine --backend c --targetDir ../emulator --compile --test --genHarness`

### Memo
The type of memory that's inferred is based on how you handle the read and write enables. This is pretty much the same as how Xilinx and Altera infer memories.
`run Memo --backend c --targetDir ../emulator --compile --test --genHarness`

### Mul
`run Mul --backend c --targetDir ../emulator --compile --test --genHarness`

### RealGCD
`run RealGCD --backend c --targetDir ../emulator --compile --test --genHarness`
