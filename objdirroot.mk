# If we have an explicit version, 
#  that will be tagged on to the output directory name.
ifneq (,$(chisel3Version))
objdirext := _$(chisel3Version)
else
objdirext := _3.0
endif

objdirroot ?= $(abspath $(top_srcdir)/generated$(objdirext))
