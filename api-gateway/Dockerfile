# ========== Build Stage ==========
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Install gradle
RUN apk add --no-cache gradle

# Copy gradle config files
COPY build.gradle settings.gradle gradle.properties ./

# Download dependencies
RUN gradle dependencies -q

# Copy source code
COPY src/ ./src/

# Build application
RUN gradle clean bootJar -x test


# ========== Runtime Stage ==========
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy JAR từ build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Health check
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
    CMD java -cp /app/app.jar org.springframework.boot.loader.JarLauncher --version || exit 1

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
