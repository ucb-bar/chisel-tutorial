ALLSETS	= csxxx-01 csxxx-02 csxxx-03
.PHONY:	$(ALLSETS)

$(ALLSETS):	SBT_FLAGS += -DchiselVersion="2.3-SNAPSHOT"

%.out: %.scala
	if ! ( set -e -o pipefail; $(SBT) $(SBT_FLAGS) "run $(notdir $(basename $<)) --genHarness --compile --test --backend c $(CHISEL_FLAGS)" | tee $@ ) \
	then \
	  mv $@ $@.fail && false ;\
	fi

default:	$(ALLSETS)

csxxx-01:	Max2.out Accumulator.out

csxxx-02:	Counter.out

csxxx-03:	VendingMachine.out

