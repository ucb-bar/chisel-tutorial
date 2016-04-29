# Chisel3 directory
chisel3	?= $(top_srcdir)/../chisel3
# Directory containing executables
bindir	?= $(abspath $(chisel3)/bin)
export CHISEL_BIN=$(bindir)

# Directory containing include files
incdir	?= $(abspath $(chisel3)/src/main/resources)

filter	?= $(bindir)/filter
firrtl	?= $(bindir)/firrtl
genharness ?= $(bindir)/gen-harness
flollvm ?= flo-llvm

vpath %.h $(incdir)

tut_firs    := $(addsuffix .fir, $(executables))

firs: $(tut_firs)

.PHONY:	firs

$(objdir)/%.out: $(objdir)/%
	"$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) --test --targetDir $(objdir)" | tee $@

$(objdir)/%:	$(objdir)/%.flo
	set -e -o pipefail; (cd $(objdir); $(bindir)/flo2app.sh $(notdir $(basename $<))) | tee $(objdir)/$(notdir $(basename $<)).app-out

$(objdir)/%.flo:	$(objdir)/%.fir
	set -e -o pipefail; (cd $(objdir); $(bindir)/fir2flo.sh $(notdir $(basename $<))) | tee $(objdir)/$(notdir $(basename $<)).flo-out

$(objdir)/%.v:	$(objdir)/%.fir
	cd $(objdir) && $(firrtl) -i $(<F) -o $(@F) -X verilog

$(objdir)/%.fir:	$(srcdir)/%.scala
	"$(SBT)" $(SBT_FLAGS) "run $(notdir $(basename $<)) $(CHISEL_FLAGS) --targetDir $(objdir)"
