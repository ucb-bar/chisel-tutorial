SHELL=/bin/bash

top_srcdir  ?= ..

# Override the default "make check" dependency since running tests
# in this directory will fail.
override CHECK_DEPENDENCY := compile

include $(top_srcdir)/suffix.mk
include csxxx.mk
