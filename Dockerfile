# 1. Chọn image base Java
FROM eclipse-temurin:17-jdk-alpine

# 2. Thêm metadata
LABEL maintainer="you@example.com"

# 3. Set working directory trong container
WORKDIR /app

# 4. Copy jar build sẵn vào container
COPY build/libs/*.jar app.jar

# 5. Expose port ứng dụng
EXPOSE 8082

# 6. Chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]