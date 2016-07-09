# Building the docs on osx will require to install Jinja2 and BeautifulSoup:
#     $ pip install Jinja2 BeautifulSoup
# and the different tools for text to pdf:
#     $ port install texlive-latex-extra texlive-latex-recommended \
#           texlive-htmlxml ImageMagick
#
# For building on Ubuntu 14.04 LTS, the following packages should be
# installed with "apt-get install":
#  python-bs4 imagemagick source-highlight tex4ht texlive-latex-base \
#  texlive-latex-recommended texlive-latex-extra texlive-fonts-recommended \
#  texlive-fonts-extra

PDFLATEX  := pdflatex
WWW_PAGES :=
WWW_EXTRA :=

# The following subdirectories build documentation correctly.
PDF_DIRS	:= tutorial
# Define a function to map PDF_DIRS to a PDF base name.
# Basically, every directory is the base name of the pdf except for dac12-talk.
pdf_base_name_from_dir = $(subst talks/dac12,dac12-talk,$(1))
# Define a map function to apply a function to multiple arguments.
map = $(foreach arg,$(2),$(call $(1),$(arg)))

PDFS := $(addsuffix .pdf,$(addprefix chisel-,$(call map,pdf_base_name_from_dir,$(PDF_DIRS))))

# Suffixes for tex temporary files we'll clean
TEX_SUFFIXES := 4ct 4tc aux css dvi html idv lg log out tmp xref
TEX_TEMP_FILES := $(foreach dir,$(PDF_DIRS),$(foreach suffix,$(TEX_SUFFIXES),$(dir)/$(call pdf_base_name_from_dir,$(dir)).$(suffix)))
STY_TEMP_FILES := $(foreach dir,$(PDF_DIRS),$(dir)/$(call pdf_base_name_from_dir,$(dir))_date.sty)

LATEX2MAN := latex2man
MAN_PAGES := chisel.man

srcDir    := .
installTop:= ../www
toolDir	?= ../../chisel-doc/bin

vpath %.tex $(addprefix $(srcDir)/,$(PDF_DIRS))

vpath %.mtt $(addprefix $(srcDir)/,$(PDF_DIRS))

all: $(WWW_PAGES) $(WWW_EXTRA) $(PDFS)

extra: $(WWW_EXTRA)

html: $(WWW_PAGES)

pdf: $(PDFS)

install: all

# NOTE: We follow the recommended practice of running the *latex tools twice
# so references (citations and figures) are correctly handled.
# NOTE: There are problems with running pdflatex after htlatex due to the
# manual.aux file left over by the latter. We see:
#  ./manual.tex:113: Undefined control sequence.
#  <argument> ...tring :autoref\endcsname {\@captype 
#                                                    }1
#  l.113 Figure~\ref{fig:node-hierarchy}
# This was reported at:
# http://tex.stackexchange.com/questions/117802/running-pdflatex-after-htlatex-causes-hyperref-error-undefined-control-sequence
# but apparently went away after upgrading to texlive 2013.
# It fails on ubuntu 14.04 LTS and texlive-latex-recommended 2013.20140215-1
# if we don't remove the manual.aux file
chisel-%.pdf: %.tex %_date.sty
	rm -f $(subst .tex,.aux,$<)
	cd $(dir $<) && for c in 0 1; do pdflatex -file-line-error -interaction nonstopmode -output-directory $(PWD) $(notdir $<) ; done
	mv $(subst .tex,.pdf,$(notdir $<)) $@

%.html: %.tex %_date.sty
	cd $(dir $<) && for c in 0 1; do htlatex $(notdir $<) $(PWD)/$(srcDir)/html.cfg "" -d/$(PWD)/ ; done
	mv $(subst .tex,.html,$(notdir $<)) $@~
	$(toolDir)/tex2html.py $@~ $@

%.man: %.mtt
	# cd into the directory containing the .tex file and massage it
	cd $(dir $<) && \
	sed -e "s/@VERSION@/$(RELEASE_TAG)/" -e "s/@DATE@/$(RELEASE_DATE)/" $(notdir $<) > $(basename $@).ttex ;\
	latex2man $(basename $@).ttex $@

%.html: $(srcDir)/templates/%.html $(srcDir)/templates/base.html
	$(toolDir)/jinja2html.py $(notdir $<) $@

clean:
	-rm -f $(TEX_TEMP_FILES)
	-rm -f $(STY_TEMP_FILES)
	# Remove any .{png,graffle} files that are created from pdfs
	-rm -f $(foreach gext,png graffle,$(subst .pdf,.$(gext),$(wildcard tutorial/figs/*.pdf)))
	-rm -f $(WWW_PAGES) $(PDFS) $(WWW_EXTRA) $(addsuffix .1,$(WWW_EXTRA)) $(patsubst %.html,%.css,$(WWW_EXTRA))
	-rm -f *~ *.aux *.log *.nav *.out *.snm *.toc *.vrb
	-rm -f *.jpg *.png

# Generate a date (optional) for the document based on the latest
# git commit of any of its (obvious) constituent parts.
%_date.sty:	%.tex
	for f in $(wildcard $(dir $<)*.tex); do git log -n 1 --format="%at" -- $$f; done | sort -nr | head -1 | gawk '{print "\\date{",strftime("%B %e, %Y", $$1),"}"}' > $@
	cmp $@ $(dir $<)$@ || cp $@ $(dir $<)
