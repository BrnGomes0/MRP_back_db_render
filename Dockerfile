FROM maven:3.9.6-amazoncorretto-17-debian-bookworm as BUILD

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn package -DskipTests -X

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=BUILD /app/target/api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]