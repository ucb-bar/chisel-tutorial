basedir = ../..
include ../../Makefrag
SBTARGS = --backend c --compile --test --targetDir ../emulator/solutions --genHarness

tut_out = $(addsuffix .out, $(tutorials))

all: $(tut_out)
	@echo; perl -ne 'print "  [$$1] $$ARGV \t$$2\n" if /(PASSED|FAILED)/' $(tut_out); echo

$(tutorials): %: ../../src/solutions/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialSolutions" "run $@ $(SBTARGS)"

$(tut_out): %.out: ../../src/solutions/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialSolutions" "run $* $(SBTARGS)" > ../emulator/solutions/$@

clean: 
	rm -f *.out *.o *-emulator.* $(tutorials) $(addsuffix .h, $(tutorials)) $(addsuffix .cpp, $(tutorials))

.PHONY: all clean
