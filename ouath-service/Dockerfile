FROM adoptopenjdk/openjdk14

EXPOSE 8201

ADD target/oauth-service.jar /opt/oauth-service.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar" , "oauth-service.jar", "&"]