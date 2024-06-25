### Demonstrate dropping an OpenTelemetry instrumentation into the Datadog Java tracer

---

Requirements:
* Java 11+
* [GraalVM](https://www.graalvm.org/downloads/) 21+ (to build native example)

The [CXF instrumentation](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/jaxws/jaxws-2.0-cxf-3.0/javaagent) jar from OpenTelemetry was downloaded from [Maven Central](https://repo1.maven.org/maven2/io/opentelemetry/javaagent/instrumentation/opentelemetry-javaagent-servlet-3.0/2.4.0-alpha/opentelemetry-javaagent-servlet-3.0-2.4.0-alpha.jar). This is the same jar that gets bundled into the OpenTelemetry javaagent.

The Datadog Java tracer v1.35.2 was downloaded from [GitHub](https://github.com/DataDog/dd-trace-java/releases/download/v1.35.2/dd-java-agent-1.35.2.jar)

---

To load OpenTelemetry instrumentations into the Datadog Java tracer use these JVM options:
```
-Ddd.trace.otel.enabled=true
-Dotel.javaagent.extensions=<comma-separated-list-of-jars-or-directories>
```
where the comma-separated list of jars or directories contains the instrumentations to be loaded.

You can also configure this with environment variables:
```
DD_TRACE_OTEL_ENABLED=true
OTEL_JAVAAGENT_EXTENSIONS=<comma-separated-list-of-jars-or-directories>
```

The Datadog Java tracer supports loading instrumentations built against the OpenTelemetry instrumentation API.

This includes third-party instrumentations that follow the example in https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/examples/extension/src/main/java/com/example/javaagent/instrumentation

Note: the tracer does not currently support loading instrumentations that use the OpenTelemetry *incubator* APIs.

---

To start the server process with the Datadog tracer attached and the CXF instrumentation from OpenTelemetry dropped in:
```
./mvnw -Pserver

# -javaagent:jars/dd-java-agent-1.35.2.jar
# -Ddd.trace.otel.enabled=true
# -Dotel.javaagent.extensions=jars/opentelemetry-javaagent-jaxws-2.0-cxf-3.0-2.4.0-alpha.jar
# -Dotel.instrumentation.common.experimental.controller-telemetry.enabled=true
```
This also enables OpenTelemetry's experimental controller telemetry, which creates a span for every message handled.

To start the client process with the Datadog tracer attached:
```
./mvnw -Pclient

# -javaagent:jars/dd-java-agent-1.35.2.jar
```

You should see the following spans:
* Client span
* Server span
* Message span (when the experimental controller telemetry is enabled)

---

To build the server as a native image with the Datadog tracer attached and the CXF instrumentation dropped in:
```
./mvnw -Pnative-server
```
This will also run the native-image of the server: `./cxf-jaxws-demo/target/cxf-jaxws-demo-server`

The client process can be started as before:
```
./mvnw -Pclient
```

