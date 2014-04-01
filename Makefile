top_srcdir  ?= .

check: solutions examples
	$(top_srcdir)/sbt/check `find solutions examples -name '*.out'` > test-solutions.xml

solutions:
	cd solutions && $(MAKE)

examples:
	cd examples && $(MAKE)

counter:
	cd counter && $(MAKE)

clean:
	cd problems && $(MAKE) clean
	cd solutions && $(MAKE) clean
	cd examples && $(MAKE) clean
	cd counter && $(MAKE) clean
	-rm -f test-solutions.xml


.PHONY: solutions examples counter
