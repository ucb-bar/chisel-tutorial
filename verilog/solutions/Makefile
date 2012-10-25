basedir = ../..
include ../../Makefrag
SBTARGS = --backend v --targetDir ../verilog/solutions --genHarness

tut_v = $(addsuffix .v, $(tutorials))

all: $(tut_v)

$(tut_v): %.v: ../../src/solutions/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialSolutions" "run $* $(SBTARGS)"

clean: 
	rm -f *.out *.o *-harness.* $(tutorials) $(addsuffix .v, $(tutorials)) *.conf
