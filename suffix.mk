SBT          ?= sbt
SBT_FLAGS    ?= -Dsbt.log.noformat=true

# Don't use any built-in rules.
MAKEFLAGS += --no-builtin-rules
.SUFFIXES:

# If a chiselVersion is defined, use that.
# Otherwise, use the latest release.
ifneq (,$(chiselVersion))
SBT_FLAGS += -DchiselVersion="$(chiselVersion)"
objdirext := _$(chiselVersion)
else
SBT_FLAGS += -DchiselVersion="latest.release"
endif

top_srcdir  ?= ..
srcdir      ?= .

include $(top_srcdir)/objdirroot.mk

# Determine where we are
curdir	:= $(abspath $(CURDIR))
# Determine which component we are
component ?= $(notdir $(patsubst %/,%,$(curdir)))
# Directory containing src
srcdir      ?= $(top_srcdir)/$(component)
# Directory below which we'll create all generated files.
include $(top_srcdir)/objdirroot.mk

objdir	?= $(objdirroot)/$(component)

units := $(filter-out solutions problems examples Image Sound,\
            $(notdir $(basename $(wildcard $(srcdir)/*.scala))))

.PHONY:	$(units)

executables := $(addprefix $(objdir)/,$(units))

generated_suffixes := .cpp .fir .flo .o .out .stdin .vcd
generated_suffixes_emulator := .cpp .o
generated_suffixes_lib := .cpp .o .so
emulator_includes := emulator.h emulator_api.h emulator_mod.h

generated_files := $(foreach sfx,$(generated_suffixes),$(addsuffix $(sfx),$(units))) $(units) \
	$(foreach sfx,$(generated_suffixes_lib),$(addsuffix $(sfx),$(addprefix lib,$(units)))) \
	$(foreach sfx,$(generated_suffixes_emulator),$(addsuffix $(sfx),$(addsuffix -emulator,$(units)))) \
	$(objdir)/test-solutions.xml

tut_outs    := $(addsuffix .out, $(executables))

default: all

all: outs verilog # dreamer

check: $(objdir)/test-solutions.xml

clean: $(filter-out $(wildcard $objdir),$(objdir)) $(objdir)
	cd $(objdir) && $(RM) $(generated_files)
	$(RM) -r project/target/ target/

outs: $(tut_outs)

dreamer: $(addsuffix .hex, $(executables))

verilog: $(addsuffix .v, $(executables))

$(objdir)/test-solutions.xml: $(tut_outs)
	$(top_srcdir)/sbt/check $(tut_outs) > $@

# We need to set the shell options -e -o pipefail here or the exit
# code will be the exit code of the last element of the pipeline - the tee.
# We should be able to do this with .POSIX: or .SHELLFLAGS but they don't
# appear to be supported by Make 3.81
ifeq (3.0,$(chiselVersion))
# Chisel3 directory
include $(top_srcdir)/chisel3/rules.mk
else
$(objdir)/%.dot: %.scala
	set -e -o pipefail; "$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --backend dot --targetDir $(objdir) $(CHISEL_FLAGS)"

$(objdir)/%.out: %.scala
	set -e -o pipefail; "$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --compile --test --backend c --vcd --targetDir $(objdir) $(CHISEL_FLAGS)" | tee $@

$(objdir)/%.hex: %.scala
	"$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --backend flo --genHarness --compile --test --targetDir $(objdir) $(CHISEL_FLAGS)"

$(objdir)/%.v: %.scala
	"$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --backend v --targetDir $(objdir) $(CHISEL_FLAGS)"
endif

compile smoke:
	"$(SBT)" $(SBT_FLAGS) compile

.PHONY: all check clean outs verilog smoke

# Last resort target. The first dependency will be the object directory,
# but only if it doesn't already exist.
# The dummy recipe seems to be required for Yosemite versions of Make,
# otherwise it complains about not being able to make the target.
%: $(filter-out $(wildcard $objdir),$(objdir)) $(objdir)/%
	@true

# Retain all intermediate files.
.SECONDARY:

# Optimization - Don't try seeing if these have dependencies and need to be regenerated.
Makefile : ;
%.mk :: ;

# If we don't have an output directory, here is the rule to make it.
$(objdir):
	mkdir -p $@
	if [ "$(incdir)" ] ;  then cp -p $(addprefix $(incdir)/,$(emulator_includes)) $@ ; fi
