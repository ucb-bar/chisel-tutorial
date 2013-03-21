basedir = ../..
include ../../Makefrag
SBTARGS = --backend c --compile --test --targetDir ../emulator/solutions --genHarness

tut_out = $(addsuffix .out, $(tutorials))

check: test-solutions.xml

test-solutions.xml: $(tut_out)
	../../sbt/check $(tut_out) > test-solutions.xml

all: $(tut_out)
	@echo; perl -ne 'print "  [$$1] $$ARGV \t$$2\n" if /(PASSED|FAILED)/' $(tut_out); echo

$(tutorials): %: ../../src/solutions/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorialSolutions" "run $@ $(SBTARGS)"

$(tut_out): %.out: ../../src/solutions/%.scala
	-cd $(basedir)/sbt; $(SBT) "project tutorialSolutions" "run $* $(SBTARGS)" > ../emulator/solutions/$@

clean: 
	rm -f *.out *.o emulator.h *-emulator.* test-*.xml $(tutorials) $(addsuffix .h, $(tutorials)) $(addsuffix .cpp, $(tutorials)) ../out.wav ../out.im24

.PHONY: all clean
