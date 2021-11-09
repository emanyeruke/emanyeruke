FROM adoptopenjdk/openjdk14

EXPOSE 8101

ADD target/file-storage-service-0.0.1-SNAPSHOT.jar /opt/file-storage-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar" , "file-storage-service-0.0.1-SNAPSHOT.jar", "&"]