SBT          := sbt
CHISEL_FLAGS :=
 
top_srcdir  ?= ..
srcdir      ?= .

executables := $(filter-out examples Image Sound MultiClockDomain,\
            $(notdir $(basename $(wildcard $(srcdir)/*.scala)))) SimpleALU

wrappers := $(addsuffix Wrapper, GCD Adder4 Risc)

all: counterc counterw counterv counterfpga

clean:
	-rm -f out.im24 out.wav *.h *.hex *.flo *.cpp *.o *.out *.v *.vcd $(executables) $(wrappers)
	-rm -rf project/target/ target/

counterc: $(addsuffix .out, $(executables))
counterw: $(addsuffix .out, $(wrappers))
counterv: $(addsuffix .v, $(executables))
counterfpga: $(addsuffix .v, $(wrappers))

%.out: %.scala
	$(SBT) "run $(notdir $(basename $@)) --genHarness --compile --test --debug --backend counterc $(CHISEL_FLAGS)" | tee $@

%Wrapper.out: %.scala
	$(SBT) "run $(notdir $(basename $@)) --genHarness --compile --test --debug --backend counterw $(CHISEL_FLAGS)" | tee $@

%.v: %.scala
	$(SBT) "run $(notdir $(basename $@)) --backend counterv $(CHISEL_FLAGS)"

%Wrapper.v: %.scala
	$(SBT) "run $(notdir $(basename $@)) --backend counterfpga $(CHISEL_FLAGS)"

.PHONY: default all clean counterc counterw counterv counterfpga
