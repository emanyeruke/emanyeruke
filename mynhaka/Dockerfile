FROM adoptopenjdk/openjdk14

EXPOSE 8100

ADD target/mynhaka-0.0.1-SNAPSHOT.jar /opt/mynhaka-0.0.1-SNAPSHOT.jar
COPY ./company-invoice.jrxml /opt/
COPY ./invoice-test.jrxml /opt/
COPY ./nhaka-logo.png /opt/

WORKDIR /opt


ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "mynhaka-0.0.1-SNAPSHOT.jar", "&"]