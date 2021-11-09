FROM adoptopenjdk/openjdk14

EXPOSE 8200

ADD target/policy-service-1.0-SNAPSHOT.jar /opt/policy-service-1.0-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "policy-service-1.0-SNAPSHOT.jar", "&"]