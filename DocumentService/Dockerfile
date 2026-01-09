FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar
COPY build/libs/*.jar app.jar

# Cài wget và dockerize
RUN apt-get update && apt-get install -y wget \
    && wget https://github.com/jwilder/dockerize/releases/download/v0.9.6/dockerize-linux-amd64-v0.9.6.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.9.6.tar.gz \
    && rm dockerize-linux-amd64-v0.9.6.tar.gz

# Expose port
EXPOSE 9050

# Chạy app sau khi MySQL sẵn sàng
ENTRYPOINT ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "30s", "java", "-jar", "app.jar"]



#FROM eclipse-temurin:17-jre
#WORKDIR /app
#
## Copy jar
#COPY build/libs/*.jar app.jar
#
## Copy wait-for-it.sh
#COPY wait-for-it.sh /wait-for-it.sh
#RUN chmod +x /wait-for-it.sh
#
## Cài netcat-openbsd và bash
#RUN apt-get update && apt-get install -y netcat-openbsd bash
#
## Expose port
#EXPOSE 9050
#
## Chạy app sau khi MySQL sẵn sàng
#ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--timeout=30", "--", "java", "-jar", "app.jar"]
