FROM adoptopenjdk/openjdk14

EXPOSE 8765

ADD target/gateway-service-0.0.1-SNAPSHOT.jar /opt/gateway-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar" , "gateway-service-0.0.1-SNAPSHOT.jar", "&"]