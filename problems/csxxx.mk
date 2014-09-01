ALLSETS	= getting-started csxxx-01 csxxx-02
.PHONY:	$(ALLSETS)

$(ALLSETS):	SBT_FLAGS += -DchiselVersion="$(CHISEL_DEFAULT_VERSION)"

%.out: %.scala
	if ! ( set -e -o pipefail; $(SBT) $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --compile --test --backend c $(CHISEL_FLAGS)" | tee $@ ) \
	then \
	  mv $@ $@.fail && false ;\
	fi

default:	$(ALLSETS)

getting-started:	Accumulator.out LFSR16.out VecShiftRegisterSimple.out Adder.out Max2.out DynamicMemorySearch.out VecShiftRegisterParam.out Mul.out 

csxxx-01:	Counter.out

csxxx-02:	VendingMachine.out

