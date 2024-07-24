# Apache Camel IBM MQ component

This Apache Camel components allows you to deal directly with IBM MQ without using the JMS wrapping.
It natively uses the MQ API to consume and produce messages on the destinations.

The component provides both consumer and producer endpoints.

## Building & Installing

To build camel-wmq component, simple do:

```
mvn clean install
```

Camel WMQ supports IBM MQ Client 9.x.

If you are using Karaf, you have to wrap and install IBM MQ AllClient artifacts before installing camel-wmq:

```
karaf@root()> bundle:install -s wrap:mvn:com.ibm.mq/com.ibm.mq.allclient/9.3.0.20
```

then you can install camel-wmq (assuming camel is already installed):

```
karaf@root()> bundle:install -s mvn:org.apache-extras.camel-extra/camel-wmq/4.7.0
```

## Usage

### URI

The endpoint URI is:

```
wmq:type:name
```

where:
* `type` is optional and the destination type. It can be either `queue` or `topic` (if not provided, the component assumes
  it's a queue.
* `name` is the destination name.

Here's a couple of URI examples:

```
wmq:queue:foo
wmq:topic:bar
wmq:foo
```

Optionally, you can provide the MQ QueueManager connection properties (see next section for details).

### MQ Connection Configuration

We have two ways to configure the connection to the MQ QueueManager.

#### Using URI parameters

You can specify the MQ QueueManager connection properties directly on the URI:
* `queueManagerName` is the name of the MQ QueueManager. If not specify, the component to fallback to "default".
* `queueManagerHostname` is the hostname of the MQ QueueManager.
* `queueManagerPort` is the port of the MQ QueueManager.
* `queueManagerChannel` is the channel of the MQ QueueManager.
* `queueManagerUserID` is the user ID (optional, only required for authentication)
* `queueManagerPassword` is the user ID (optional, only required for authentication)
* `queueManagerCCSID` is the CCSID (optional, only required for authentication)

The component manages multiple MQ QueueManager connection, identified by the QueueManager name.

If the `queueManagerHostname`, `queueManagerPort`, and `queueManagerChannel` properties are not specified on the URI,
the component falls back on the mq.properties.

#### Using mq.properties

If the properties are not specified on the URI, the component loads a `mq.properties` file from the classloader, or
from KARAF_HOME/etc folder of the Karaf/runtime.

Here's an example of `mq.properties`:

```
default.hostname=localhost
default.port=7777
default.channel=QM_TEST.SVRCONN
```

The `mq.properties` file can contain multiple MQ QueueManagers definition. The format is:

```
name.hostname
name.port
name.channel
```

where `name` is the MQ QueueManager name, as defined with the `queueManagerName` on the URI.

For instance, the `mq.properties` file can contain:

```
default.hostname=localhost
default.port=7777
default.channel=DEFAULT.SVRCONN
test.hostname=localhost
test.port=7778
test.channel=QM_TEST.SVRCONN
```

The `mq.properties` also supports the userID, password, and CCSID properties (optional):

```
default.hostname=localhost
default.port=7777
default.channel=DEFAULT.SVRCONN
default.userID=mqm
default.password=mqm
default.CCSID=1208
```

## Message Body & Headers

The WMQ consumer endpoint populates the body of the Camel in message with the payload of the MQ message.

On the other hand, the WMQ producer endpoint sends a MQ message with payload populated with the Camel in message body.

Additionally, both endpoints support the following headers (the consumer populates these headers, and the producer
uses it if present):

* `mq.mqmd.format`: the message MQMD format
* `mq.mqmd.charset`: the message MQMD character set
* `mq.mqmd.expiry`: the message MQMD expiry
* `mq.mqmd.put.appl.name`: the message MQMD put application name
* `mq.mqmd.group.id`: the message MQMD group ID
* `mq.mqmd.msg.seq.number`: the message MQMD sequence number
* `mq.mqmd.msg.accounting.token`: the message MQMD accounting token
* `mq.mqmd.correl.id`: the message MQMD correlation ID
* `mq.mqmd.replyto.q`: the message MQMD ReplyTo queue name
* `mq.mqmd.replyto.q.mgr`: the message MQMD ReplyTo queue manager name
* `mq.rfh2.format`: the message RFH2 format (optional)
* `mq.rfh2.struct.id`: the message RFH2 struct ID
* `mq.rfh2.encoding`: the message RFH2 encoding
* `mq.rfh2.coded.charset.id`: the message RFH2 coded character set ID
* `mq.rfh2.flags`: the message RFH2 flags
* `mq.rfh2.version`: the message RFH2 version
* `mq.rfh2.folder.[FOLDER_NAME]`: the message RFH2 folder, where the [FOLDER_NAME] can be MCD, JMS, USR, PSC, PSCR, OTHER (depending of the content of the message).
