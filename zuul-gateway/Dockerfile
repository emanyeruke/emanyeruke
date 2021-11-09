FROM adoptopenjdk/openjdk14

EXPOSE 8765

ADD target/zuul-gateway-0.0.1-SNAPSHOT.jar /opt/zuul-gateway-0.0.1-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar" , "zuul-gateway-0.0.1-SNAPSHOT.jar", "&"]