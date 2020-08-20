FROM maven:3.5.2-jdk-8-alpine as maven_build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
RUN mvn dependency:resolve-plugins
COPY . .
RUN mvn clean package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=maven_build /app/target/ /app
CMD [ "java", "-jar", "sportsbook-0.0.1-SNAPSHOT.jar" ]

