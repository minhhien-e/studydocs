# Base image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ENV SERVER_PORT=9051

# Copy built jar from host (requires local build first)
COPY build/libs/*.jar app.jar

# Expose port
EXPOSE ${SERVER_PORT}

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
