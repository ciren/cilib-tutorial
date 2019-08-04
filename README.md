# Note:

This project will be folded into the main project repository and will
preferably be served online.

# Community tutorial for CIlib

[![Build Status](https://travis-ci.org/cirg-up/cilib-tutorial.svg?branch=master)](https://travis-ci.org/cirg-up/cilib-tutorial)

Welcome to the community tutorial source repository!

## About the tutorial

The tutorial is a community project detailing the use of the CIlib
library, and the explanation of some of the concepts that are used
within the library.

## Building the sources and contributions

PRs are welcome! All changes will be be reviewed before inclusion, but
the review process will be transparent and open.

To build the project, you can either install the needed software on
your local machine, or you can use the docker baced build process.

The book uses the build tool developed by underscore.io to convert
markdown sources into a final PDF (through the use of Pandoc and
LaTeX)

You'll need to have docker (>= 1.8.0) installed (and by extension have
`docker-compose` available) locally and be familiar with how docker
operates.

To build the documentation, please use the `build.sh` script (or use
the relevant `docker` commands manually, if preferred) which will
build the sources, and also start needed containers.

Please remember to run `docker-compose down` when you are done!
