SBT          ?= sbt
SBT_FLAGS    ?= -Dsbt.log.noformat=true

# If a chiselVersion is defined, use that.
# Otherwise, use the latest release.
ifneq (,$(chiselVersion))
SBT_FLAGS += -DchiselVersion="$(chiselVersion)"
else
SBT_FLAGS += -DchiselVersion="latest.release"
endif

CHISEL_FLAGS := --minimumCompatibility 3.0.0

top_srcdir  ?= ..
srcdir      ?= .

executables := $(filter-out solutions problems examples Image Sound,\
            $(notdir $(basename $(wildcard $(srcdir)/*.scala))))

tut_outs    := $(addsuffix .out, $(executables))


default: all

all: emulator verilog # dreamer

check: test-solutions.xml

clean:
	-rm -f out.im24 out.wav *.h *.hex *.flo *.cpp *.o *.out *.v *.vcd $(executables)
	-rm -rf project/target/ target/

emulator: $(tut_outs)

dreamer: $(addsuffix .hex, $(executables))

verilog: $(addsuffix .v, $(executables))

test-solutions.xml: $(tut_outs)
	$(top_srcdir)/sbt/check $(tut_outs) > $@

# We need to set the shell options -e -o pipefail here or the exit
# code will be the exit code of the last element of the pipeline - the tee.
# We should be able to do this with .POSIX: or .SHELLFLAGS but they don't
# appear to be support by Make 3.81
%.out: %.scala
	set -e -o pipefail; "$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --compile --test --backend c --vcd $(CHISEL_FLAGS)" | tee $@

%.hex: %.scala
	"$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --backend flo --genHarness --compile --test $(CHISEL_FLAGS)"

%.v: %.scala
	"$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --backend v $(CHISEL_FLAGS)"

smoke:
	"$(SBT)" $(SBT_FLAGS) compile

.PHONY: all check clean emulator verilog smoke
