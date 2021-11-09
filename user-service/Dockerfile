FROM adoptopenjdk/openjdk14

EXPOSE 8202

ADD target/usermanager.jar /opt/usermanager.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar" , "-Dspring.profiles.active=dev","usermanager.jar", "&"]