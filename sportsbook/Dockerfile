FROM maven:3.6-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package -DskipTests

FROM openjdk:8-alpine

WORKDIR /app
EXPOSE 8080
COPY --from=MAVEN_BUILD /build/target/sportsbook-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java", "-jar",  "sportsbook-0.0.1-SNAPSHOT.jar"]
