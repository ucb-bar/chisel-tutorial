SBT          := sbt
SBT_FLAGS    ?=
CHISEL_FLAGS :=
DEBUG_FLAGS  := $(CHISEL_FLAGS) --debug --vcd 
top_srcdir  ?= ..
srcdir      ?= .

executables := $(filter-out examples Image Sound MultiClockDomain,\
            $(notdir $(basename $(wildcard $(srcdir)/*.scala))))

wrappers := $(addsuffix Wrapper, GCD Adder4 Risc)

all: counterc counterw counterv counterfpga

clean:
	-rm -f out.im24 out.wav *.h *.hex *.flo *.cpp *.o *.out *.v *.vcd *.debug $(executables) $(wrappers) SimpleALU
	-rm -rf project/target/ target/

counterc: $(addsuffix .out, $(executables))
counterw: $(addsuffix .out, $(wrappers))
counterv: $(addsuffix .v, $(executables))
#counterfpga: $(addsuffix .v, $(wrappers))
counterc-debug: $(addsuffix .debug, $(executables))
counterw-debug: $(addsuffix .debug, $(wrappers))

%.out: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $@)) --genHarness --compile --test --backend counterc $(CHISEL_FLAGS)" | tee $@

%Wrapper.out: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $@)) --genHarness --compile --test --backend counterw $(CHISEL_FLAGS)" | tee $@

%.debug: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $@)) --genHarness --compile --test --backend counterc $(DEBUG_FLAGS)" | tee $@

%Wrapper.debug: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $@)) --genHarness --compile --test --backend counterw $(DEBUG_FLAGS)" | tee $@

%.v: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $@)) --backend counterv $(CHISEL_FLAGS)"

%Wrapper.v: %.scala
	$(SBT) $(SBT_FLAGS) "run $(notdir $(basename $@)) --backend counterfpga $(CHISEL_FLAGS)"

smoke:
	$(SBT) $(SBT_FLAGS) compile

.PHONY: default all clean counterc counterw counterv counterfpga counterc-debug counterw-debug 
