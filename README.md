# threescale-policy-processor

## Packaging and running the application - OpenShift
mvn package -Dquarkus.kubernetes.deploy=true -Dquarkus.kubernetes-client.trust-certs=true -Dquarkus.s2i.base-jvm-image=registry.access.redhat.com/ubi9/openjdk-17

# See also
https://github.com/msoares94/3scale-custom-policy-example/tree/main/kafka-producer
