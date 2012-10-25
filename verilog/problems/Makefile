basedir = ../..
include ../../Makefrag
SBTARGS = --backend v --targetDir ../verilog/problems --genHarness

tut_v = $(addsuffix .v, $(tutorials))

all: $(tut_v)

$(tut_v): %.v: ../../src/problems/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialProblems" "run $* $(SBTARGS)"

clean: 
	rm -f *.out *.o *-harness.* $(tutorials) $(addsuffix .v, $(tutorials)) *.conf
