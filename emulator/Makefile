basedir = ..
include ../Makefrag
SBTARGS = --backend c --compile --test --targetDir ../emulator --genHarness

tut_out = $(addsuffix .out, $(tutorials))

all: $(tut_out)
	@echo; perl -ne 'print "  [$$1] $$ARGV \t$$2\n" if /(PASSED|FAILED)/' $(tut_out); echo

$(tutorials): %: ${CHISEL}/src/*.scala ../src/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorial" "run $@ $(SBTARGS)"

$(tut_out): %.out: ${CHISEL}/src/*.scala ../src/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorial" "run $* $(SBTARGS)" > $(basedir)/emulator/$@

clean: 
	rm -f *.out *.o $(tutorials) $(addsuffix .h, $(tutorials)) $(addsuffix .cpp, $(tutorials))

.PHONY: all clean
