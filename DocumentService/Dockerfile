# Base image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built jar from host (requires local build first)
COPY build/libs/*.jar app.jar

# Expose port
EXPOSE 8085

# Start directly (rely on docker-compose depends_on for DB readiness)
ENTRYPOINT ["java", "-jar", "app.jar"]
