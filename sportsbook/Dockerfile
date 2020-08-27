FROM maven:3.6-jdk-8-alpine

WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/


RUN mvn clean package
#CMD ["maven", "package"]

FROM openjdk:8-alpine

WORKDIR /app
EXPOSE 8080
COPY --from=MAVEN_BUILD /build/target/sportsbook-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar",  "usr/app/sportsbook-0.0.1-SNAPSHOT.jar"]
