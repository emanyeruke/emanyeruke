FROM adoptopenjdk/openjdk14

EXPOSE 8300

ADD target/policy-holder-service-0.0.1-SNAPSHOT.jar /opt/policy-holder-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "policy-holder-service-0.0.1-SNAPSHOT.jar", "&"]