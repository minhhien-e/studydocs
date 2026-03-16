FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Fix line endings and permissions
RUN apt-get update && apt-get install -y dos2unix \
    && dos2unix gradlew \
    && chmod +x gradlew

# Run build (skip tests)
RUN ./gradlew bootJar -x test

# Final Stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built jar
COPY --from=builder /app/build/libs/*.jar app.jar

# Install dockerize
RUN apt-get update && apt-get install -y wget \
    && wget https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.6.1.tar.gz \
    && rm dockerize-linux-amd64-v0.6.1.tar.gz

# Expose port
EXPOSE 8085

# Wait for MySQL (assume host 'mysql') and start
ENTRYPOINT ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "60s", "java", "-jar", "app.jar"]
