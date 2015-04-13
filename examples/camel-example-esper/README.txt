#
# Copyright (C) 2008 - 2013 Camel Extra Team. All rights reserved.
# https://camel-extra.github.io
# ----------------------------------------------------------------------------------
# The software in this package is published under the terms of the GPL license
# a copy of which has been included with this distribution in the license.txt file.
#

This a simple demo showing how to integrate the Camel Esper component with ActiveMQ

How to run:

1) in one shell just type: mvn camel:run
   This starts a java process with an embedded ActiveMQ message broker and a Camel route.
   This Camel route receives a flow of events coming from a JMS queue injecting them into a complex event processing flow
   based on a couple of Camel Esper components.
2) On another shell just type: mvn exec:java
   This starts a JMS producer that sends a stream of events to the queue hosted by the embedded message broker above.
   
This demo is based on an Exper tutorial:
http://esper.codehaus.org/tutorials/tutorial/feedmonitor_casestudy.html

look there for an exact meaning of the two Esper scripts in the demo

