## Multi-stage Dockerfile for building and running the Spring Boot app
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace/app

# copy maven wrapper and pom first for better cache
COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml

# copy source
COPY src src

RUN chmod +x mvnw || true
RUN ./mvnw -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app

# copy jar built by maven
COPY --from=builder /workspace/app/target/*.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
