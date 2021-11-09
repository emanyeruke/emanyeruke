FROM adoptopenjdk/openjdk14

EXPOSE 8761

ADD target/eureka-service-0.0.1-SNAPSHOT.jar /opt/eureka-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar", "eureka-service-0.0.1-SNAPSHOT.jar", "&"]