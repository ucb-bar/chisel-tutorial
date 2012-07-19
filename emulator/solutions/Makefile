basedir = ../..
include ../../Makefrag
SBTARGS = --backend c --compile --test --targetDir ../emulator/solutions --genHarness

tut_out = $(addsuffix .out, $(tutorials))

all: $(tut_out)
	@echo; perl -ne 'print "  [$$1] $$ARGV \t$$2\n" if /(PASSED|FAILED)/' $(tut_out); echo

$(tutorials): %: ${CHISEL}/src/*.scala ../../src/solutions/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialSolutions" "run $@ $(SBTARGS)"

$(tut_out): %.out: ${CHISEL}/src/*.scala ../../src/solutions/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialSolutions" "run $* $(SBTARGS)" > $(basedir)/emulator/solutions/$@

clean: 
	rm -f *.out *.o $(tutorials) $(addsuffix .h, $(tutorials)) $(addsuffix .cpp, $(tutorials))

.PHONY: all clean
