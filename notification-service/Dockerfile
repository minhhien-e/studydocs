# Build stage
FROM gradle:8.13-jdk17 AS build

WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradle/ gradle/
COPY gradlew .
COPY gradlew.bat .
COPY build.gradle .
COPY settings.gradle .

# Copy all source modules
COPY api/ api/
COPY application/ application/
COPY domain/ domain/
COPY infrastructure/ infrastructure/
COPY bootstrap/ bootstrap/
COPY shared/ shared/

# Build the application (skip tests for faster build, remove --no-daemon if needed)
RUN ./gradlew :bootstrap:bootJar --no-daemon -x test

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Create non-root user
RUN addgroup -S spring && adduser -S spring -G spring

# Copy the JAR from build stage
COPY --from=build /app/bootstrap/build/libs/*.jar app.jar

# Change ownership
RUN chown spring:spring app.jar

USER spring:spring

# Expose port
EXPOSE 8087

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8087/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

