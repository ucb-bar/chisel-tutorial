basedir = ../..
include ../../Makefrag
SBTARGS = --backend c --compile --test --targetDir ../emulator/problems --genHarness

tut_out = $(addsuffix .out, $(tutorials))

all: $(tut_out)
	@echo; perl -ne 'print "  [$$1] $$ARGV \t$$2\n" if /(PASSED|FAILED)/' $(tut_out); echo

$(tutorials): %: ../../src/problems/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialProblems" "run $@ $(SBTARGS)"

$(tut_out): %.out: ../../src/problems/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialProblems" "run $* $(SBTARGS)" > ../emulator/problems/$@

clean: 
	rm -f *.out *.o emulator.h *-emulator.* $(tutorials) $(addsuffix .h, $(tutorials)) $(addsuffix .cpp, $(tutorials))

.PHONY: all clean
