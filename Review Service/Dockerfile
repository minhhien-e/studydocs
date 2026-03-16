FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN rm -rf .gradle build

RUN apk add --no-cache dos2unix \
    && dos2unix gradlew \
    && chmod +x gradlew \
    && ./gradlew bootJar -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ENV SERVER_PORT=9051

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
