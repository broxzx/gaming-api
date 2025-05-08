FROM maven:3.9.4-eclipse-temurin-21 as build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM bellsoft/liberica-openjdk-debian:21

RUN adduser --system gaming-api && addgroup --system gaming-api && adduser gaming-api gaming-api
USER gaming-api

WORKDIR /app
COPY --from=build /target/gaming-api.jar ./application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./application.jar"]