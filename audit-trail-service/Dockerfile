FROM adoptopenjdk/openjdk14

EXPOSE 8208

ADD target/audit-trail-service-0.0.1-SNAPSHOT.jar /opt/audit-trail-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar" , "audit-trail-service-0.0.1-SNAPSHOT.jar", "&"]