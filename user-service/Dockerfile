# Stage 1: Build the application
FROM gradle:8-jdk17-alpine AS builder
WORKDIR /app
COPY . .
# Skip tests to speed up build
RUN gradle clean build -x test --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]