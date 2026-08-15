# Build stage
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew bootJar --no-daemon -x test

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar
COPY seed ./seed

RUN mkdir -p uploads

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
