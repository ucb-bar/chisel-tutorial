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
cd problems

### Mux2
This should already work. Try
`run Mux2 --backend c --targetDir ../emulator --compile --test`