Chisel Tutorials (Development branch)
================

These are the tutorials for [Chisel](https://github.com/ucb-bar/chisel3).

Chisel is an open-source hardware construction language developed
at UC Berkeley that supports advanced hardware design using highly
parameterized generators and layered domain-specific hardware languages.


Getting the Repo
----------------

    $ git clone https://github.com/ucb-bar/chisel-tutorial.git
    $ cd chisel-tutorial
    $ git fetch origin
    $ git checkout master


Executing Chisel
----------------

####Testing Your System
First make sure that you have sbt (the scala build tool) installed. See details
in [sbt](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

    $ sbt run

This will generate and test a simple block (`Hello`) that always outputs the
number 42 (aka 0x2a). You should see `[success]` on the last line of output (from sbt) and
`PASSED` on the line before indicating the block passed the testcase. If you
are doing this for the first time, sbt will automatically download the
appropriate versions of Chisel3, the Chisel Testers harness
and Scala and cache them (usually in `~/.ivy2`).

Completing the Tutorials
------------------------

To learn Chisel, we recommend learning by example and just trying things out.
To help with this, we have produced exercises with circuits (`src/main/scala/problems`) and their
 associated test harnesses (`src/test/scala/problems`) which have clearly
marked places to complete their functionality and simple test cases. You can
compare your work with our sample solutions in (`src/main/scala/solutions`) and (`src/test/scala/solutions`).  This 
hierarchical organization and separation of circuits and tests is a good practice and we encourage you to understand it
and use it in the future.  Typically when you work on a problem you will have two open editor windows (vi, emacs, IDE, 
etc) one to edit the circuit and the other to edit the tests.

To speed things up, we will keep sbt running. To get started:

    $ sbt

#### Mux2
This should already work. Try

    > test:runMain problems.Launcher Mux2
    
Note that `Mux2` is defined in `Mux4.scala`.

#### Mux4
You can instantiate a module with `val foo = Module(new Bar())`

    > test:runMain problems.Launcher Mux4

#### Counter
You can conditionally update a value without a mux by using `when (cond) { foo := bar }`

    > test:runMain problems.Launcher Counter

#### Vending Machine

    > test:runMain problems.Launcher VendingMachine

#### Memo
The type of memory that's inferred is based on how you handle the read and
write enables. This is pretty much the same as how Xilinx and Altera infer
memories.

    > test:runMain problems.Launcher Memo

#### Mul

    > test:runMain problems.Launcher Mul

#### RealGCD

    > test:runMain problems.Launcher RealGCD


To check that all of your solutions are correct:

    $ ./run-problem.sh all


To run all of our reference solutions:

    $ ./run-solution.sh all

Note: ./run-problem.sh, ./run-solution.sh, ./run-examples.sh are convenience scripts to invoke tests

Learning More Chisel
--------------------
In addition to the problems and the solutions, we have also provided some
examples of more complex circuits (`src/main/scala/examples`) and (`src/test/scala/examples`). You should take a 
look at the source and test them out:

    $ ./run-examples.sh all

The [wiki](https://github.com/ucb-bar/chisel-tutorial/wiki/) attached to this repo contains more information on working with Chisel.
Additional documentation may be found on the chisel3 repo
[wiki](https://github.com/ucb-bar/chisel3/wiki/)
and the
[documentation](https://chisel.eecs.berkeley.edu/documentation.html)
section of the [website](https://chisel.eecs.berkeley.edu/).

Fixes/Updates
-------------
If you wish to submit pull requests for changes to this repo, plus check out the master branch, and make your pull requests against that branch.
