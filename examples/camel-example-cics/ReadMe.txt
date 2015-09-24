#
# Copyright (C) 2008 - 2013 Camel Extra Team. All rights reserved.
# https://camel-extra.github.io
# ----------------------------------------------------------------------------------
# The software in this package is published under the terms of the GPL license
# a copy of which has been included with this distribution in the license.txt file.
#

This route uses CICS Transaction Gateway Camel Component to invoke a COBOL Program in a Mainframe

How to run:

1) in one shell just type: mvn camel:run
   This Camel route invokes every minute a Cobol program in a CICS Server. The program returns the timestamp.
