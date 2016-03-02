# If we have an explicit version, 
#  that will be tagged on to the output directory name.
ifneq (,$(chiselVersion))
objdirext := _$(chiselVersion)
endif

objdirroot ?= $(abspath $(top_srcdir)/generated$(objdirext))
