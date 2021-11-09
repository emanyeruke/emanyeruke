FROM adoptopenjdk/openjdk14

EXPOSE 8400

ADD target/payment-service-0.0.1-SNAPSHOT.jar /opt/payment-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "payment-service-0.0.1-SNAPSHOT.jar", "&"]