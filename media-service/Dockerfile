# ========================
# Build
# ========================
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy everything
COPY . .

# Grant execution rights and fix CRLF
RUN chmod +x gradlew && sed -i 's/\r$//' gradlew

# Build the application (launcher module)
RUN ./gradlew :launcher:bootJar -x test

# ========================
# Run
# ========================
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built jar from the launcher module
COPY --from=build /app/launcher/build/libs/*.jar app.jar

EXPOSE 8084
ENTRYPOINT ["java","-jar","app.jar"]
