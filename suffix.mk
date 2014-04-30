SBT          ?= sbt
SBT_FLAGS    ?= -Dsbt.log.noformat=true

# If a chiselVersion is defined, use that.
ifneq (,$(chiselVersion))
CHISEL_SMOKE_VERSION	:= $(chiselVersion)
CHISEL_CHECK_VERSION	:= $(chiselVersion)
else
CHISEL_SMOKE_VERSION	?= 2.3-SNAPSHOT
CHISEL_CHECK_VERSION	?= 2.3-SNAPSHOT
endif

CHISEL_FLAGS :=

top_srcdir  ?= ..
srcdir      ?= .

executables := $(filter-out solutions problems examples Image Sound,\
            $(notdir $(basename $(wildcard $(srcdir)/*.scala))))

tut_outs    := $(addsuffix .out, $(executables))

all: emulator verilog # dreamer

check:  SBT_FLAGS += -DchiselVersion="$(CHISEL_CHECK_VERSION)"
check: test-solutions.xml

clean:
	-rm -f out.im24 out.wav *.h *.hex *.flo *.cpp *.o *.out *.v *.vcd $(executables)
	-rm -rf project/target/ target/

emulator: $(tut_outs)

dreamer: $(addsuffix .hex, $(executables))

verilog: $(addsuffix .v, $(executables))

test-solutions.xml: $(tut_outs)
	$(top_srcdir)/sbt/check $(tut_outs) > $@

%.out: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --compile --test --backend c $(CHISEL_FLAGS)" | tee $@

%.hex: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $<)) --backend flo --genHarness --compile --test $(CHISEL_FLAGS)"

%.v: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --backend v $(CHISEL_FLAGS)"

smoke:  SBT_FLAGS += -DchiselVersion="$(CHISEL_SMOKE_VERSION)"
smoke:
	$(SBT) $(SBT_FLAGS) compile

.PHONY: all check clean emulator verilog smoke
