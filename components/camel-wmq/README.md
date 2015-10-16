Apache Camel IBM MQ component
=============================
This Apache Camel components allows you to deal directly with IBM MQ without using the JMS wrapping.
It natively uses the MQ API to consume and produce messages on the destinations.

The component provides both consumer and producer endpoints.

Building
--------
In order to be able to build the component, you have to add the IBM MQ dependencies in your local Maven repository
using the mvn install:install-file command:

    mvn install:install-file -DgroupId=com.ibm.mq -DartifactId=mq -Dversion=7.5 -Dpackaging=jar -Dfile=/path/to/mq.jar
    mvn install:install-file -DgroupId=com.ibm.mq -DartifactId=mqjms -Dversion=7.5 -Dpackaging=jar -Dfile=/path/to/mqjms.jar
    mvn install:install-file -DgroupId=com.ibm.mq -DartifactId=jmqi -Dversion=7.5 -Dpackaging=jar -Dfile=/path/to/jmqi.jar
    mvn install:install-file -DgroupId=com.ibm.mq -DartifactId=connector -Dversion=7.5 -Dpackaging=jar -Dfile=/path/to/connector.jar

MQ Connection Configuration
---------------------------
To establish the connection to the MQ broker, the component looks for a mq.properties file containing:

    hostname=
    port=
    channel=
    name=

The component tries to load the mq.properties from the classloader. If the `mq.properties` is not found in
the classloader, it tries to load from KARAF_HOME/etc folder.

URI
---
The endpoint URI is:

    wmq:destinationName

By default, the component deals with MQ queue.

You can specify the destination type (queue or topic) using the destinationType option:

    wmq:topicName?destinationType=topic


