top_srcdir  ?= .

# Directories removed by make clean
RM_DIRS 	:= emulator project/target target

# All subdirectories
ALL_SUB_DIRS	:= counter examples problems solutions

# Subdirectories that currently build cleanly,
# for use in check, smoke
TUT_SUB_DIRS	:= examples solutions

# Subdirectory targets.
SUB_DIR_TARGETS	:= check clean smoke

# Target-specific subdirectories
check_SUB_DIRS	:= $(TUT_SUB_DIRS)
clean_SUB_DIRS	:= $(ALL_SUB_DIRS)
smoke_SUB_DIRS	:= $(TUT_SUB_DIRS)

# A Make "subroutine" to build target $1 in directory $2
# We generate specifc target.subdirectory phony targets, rather
# than simply using something like:
#$(SUB_DIRS):
#	$(MAKE) -C $@ $(SUB_TARGET)
# where $(SUB_TARGET) is a target-specific variable, so things like
# 	make clean smoke
# work as expected.
# Of course, some combinations are problematic:
# 	make smoke clean
define GenSubDirTarget
$(1).$(2):
	$(MAKE) -C $(2) $(1)

# Indicate this is a dummy target
.PHONY:	$(1).$(2)
# Add this dummy to the list of targeters
_$(1)ers += $(1).$(2)
endef

# A Make "subroutine" to generate sub-directory targets for target $1
define GenSubDirTargets
$(foreach d,$($(1)_SUB_DIRS),$(eval $(call GenSubDirTarget,$1,$d)))
# Make this target dependent on all the generated sub-directory targets
$(1):	$$(_$(1)ers)
endef

# Generate the cross product of target_SUB_DIRS and SUB_DIR_TARGETS
$(foreach t,$(SUB_DIR_TARGETS),$(eval $(call GenSubDirTargets,$t)))

.PHONY: $(ALL_SUB_DIRS) check clean jenkins-build smoke

# Generate the generic "make default in sub-directory".
$(ALL_SUB_DIRS):
	$(MAKE) -C $@

# GenSubDirTargets generates a dependency:
# check: $(_checkers)
check:
	$(top_srcdir)/sbt/check `find solutions examples -name '*.out'` > test-solutions.xml

jenkins-build:	clean smoke check

# GenSubDirTargets generates a dependency:
# clean: $(_cleaners)
clean:
	$(RM) test-solutions.xml
	$(RM) -r $(RM_DIRS)

# GenSubDirTargets generates dependencies:
# smoke: $(_smokeers)
