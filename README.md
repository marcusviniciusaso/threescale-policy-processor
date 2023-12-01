# code-with-quarkus Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Camel Kafka ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/kafka.html)): Sent and receive messages to/from an Apache Kafka broker
- Camel HTTP ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/http.html)): Send requests to external HTTP servers using Apache HTTP Client 5.x
- Camel Core ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/core.html)): Camel core functionality and basic Camel languages: Constant, ExchangeProperty, Header, Ref, Simple and Tokenize


## Packaging and running the application - OpenShift
mvn package -Dquarkus.kubernetes.deploy=true -Dquarkus.kubernetes-client.trust-certs=true -Dquarkus.s2i.base-jvm-image=registry.access.redhat.com/ubi9/openjdk-17

## Message Topic
{
    "request": {
        "headers": {
            "accept": "*/*",
            "accept-encoding": "gzip, deflate, br",
            "forwarded": "for=168.205.241.14;host=api-3scale-apicast-staging.apps.cluster-rxdvg.rxdvg.sandbox941.opentlc.com:443;proto=https",
            "host": "echo-api.3scale.net",
            "postman-token": "c35891a4-1126-4975-b5cf-d57e931451a4",
            "user-agent": "PostmanRuntime/7.33.0",
            "x-forwarded-for": "168.205.241.14",
            "x-forwarded-host": "api-3scale-apicast-staging.apps.cluster-rxdvg.rxdvg.sandbox941.opentlc.com:443",
            "x-forwarded-port": "443",
            "x-forwarded-proto": "https"
        },
        "id": "6857189f8c98e0a4e8457ebe124100cc",
        "method": "GET",
        "query": {
            "user_key": "d6718dc5aabd0a86c0fc1530aedc5963"
        },
        "request_body_size": "590",
        "request_headers_size": 476,
        "uri": "/"
    },
    "response": {
        "headers": {
            "connection": "keep-alive",
            "content-length": "1131",
            "content-type": "application/json",
            "vary": "Origin",
            "x-3scale-echo-api": "echo-api/1.0.3",
            "x-content-type-options": "nosniff",
            "x-envoy-upstream-service-time": "0"
        },
        "response_body_size": "1400",
        "response_headers_size": 183,
        "status": 200
    },
    "service_id": "2",
    "transaction_size": 2649,
    "user_information": {
        "user_key": "d6718dc5aabd0a86c0fc1530aedc5963"
    }
}

# Example Create app in OpenShift
oc new-app --name threescale-policy-processor --image=quay.io/msoares/threescale-policy-processor --param=THEESCALE_API_REST=https://3scale-admin.apps.cluster-rxdvg.rxdvg.sandbox941.opentlc.com --param=KAFKA_BOOTSTRAP_SERVERS=kafka-cluster-kafka-bootstrap.kafka.svc.cluster.local:9092 --param=KAFKA_TOPIC_INCOMING=3scale-policy-kafka-producer --param=KAFKA_TOPIC_INCOMING_GROUPID=grp-3scale-policy-kafka-producer --param=KAFKA_TOPIC_OUTGOING=3scale-analytics --param=KAFKA_TOPIC_OUTGOING_GROUPID=grp-3scale-analytics --param=THEESCALE_API_TOKEN=my-token