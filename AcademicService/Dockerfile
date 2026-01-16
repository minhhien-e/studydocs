# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy maven wrapper and pom code
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies (go offline to cache dependencies)
# This step is optional but speeds up subsequent builds
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
# Skip tests to speed up the build (optional, remove -DskipTests if you want to run tests)
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (default Spring Boot port is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
