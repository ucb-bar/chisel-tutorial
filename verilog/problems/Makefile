basedir = ..
include ../../Makefrag
SBTARGS = --backend v --targetDir ../verilog/problems --genHarness

tut_v = $(addsuffix .v, $(tutorials))

all: $(tut_v)

$(tut_v): %.v: ${CHISEL}/src/problems/*.scala ../src/problems/%.scala
	cd $(basedir)/sbt; $(SBT) "project tutorial" "run $* $(SBTARGS)"

clean: 
	rm -f *.out *.o $(tutorials) $(addsuffix .v, $(tutorials)) *.conf
