SBT          := sbt
CHISEL_FLAGS :=

top_srcdir  ?= ..
srcdir      ?= .

executables := $(filter-out tutorial examples Image Sound,\
            $(notdir $(basename $(wildcard $(srcdir)/src/main/scala/*.scala))))

tut_outs    := $(addsuffix .out, $(executables))

all: emulator verilog

check: test-solutions.xml

clean:
	-rm -f out.im24 out.wav *.h *.cpp *.o *.out *.v $(executables)
	-rm -rf project/target/ target/

emulator: $(tut_outs)

verilog: $(addsuffix .v, $(executables))


vpath %.scala $(srcdir)/src/main/scala

test-solutions.xml: $(tut_outs)
	$(top_srcdir)/sbt/check $(tut_outs) > $@

%.out: %.scala
	$(SBT) "run $(notdir $(basename $<)) --genHarness --compile --test --backend c  $(CHISEL_FLAGS)" | tee $@

%.v: %.scala
	$(SBT) "run $(notdir $(basename $<)) --genHarness --backend v $(CHISEL_FLAGS)"

.PHONY: all check clean emulator verilog
