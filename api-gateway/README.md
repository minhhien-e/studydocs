# API Gateway - StudyDocs

## 1. Giới thiệu tổng quan
Đây là API Gateway của hệ thống StudyDocs, đóng vai trò là điểm vào duy nhất (Single Entry Point) cho toàn bộ các request từ phía client (Mobile App, Web App) đến các microservices bên trong hệ thống.

**Chức năng chính:**
- **Định tuyến (Routing):** Chuyển tiếp các yêu cầu đến các microservices tương ứng dựa trên đường dẫn (Path-based routing).
- **Yêu cầu Xác thực & Cấp quyền:** Tích hợp với Auth Service để kiểm tra tính hợp lệ của JWT (JSON Web Token).
- **Kiểm soát lưu lượng (Rate Limiting):** Giới hạn số lượng request từ mỗi IP để tránh tấn công DoS/DDoS và quá tải hệ thống.
- **Khả năng chịu lỗi (Resilience):** Sử dụng Circuit Breaker để ngăn chặn lỗi lan truyền giữa các service.
- **Quan sát hệ thống (Observability):** Tích hợp Distributed Tracing (Brave/Zipkin) để theo dõi luồng request.

Service đang chạy tại port **8080**.

---

## 2. Chức năng chi tiết

### Định tuyến & Cân bằng tải
API Gateway định tuyến các yêu cầu đến các microservices sau:
- **User Service:** `/users/**` → `http://user-service:8082`
- **Auth Service:** `/authentica/**` → `http://auth-service:8081`
- **Notification Service:** `/notifications/**` → `http://notification-service:8087`
- **Upload Service:** `/upload/**` → `http://upload-service:8084`
- **Review Service:** `/review/**` → `http://review-service:8086`
- **Follow Service:** `/follow/**` → `http://follow-service:8089`
- **Academic Service:** `/academic/**` → `http://academic-service:8083`
- **Search Service:** `/search/**` → `http://search-service:8088`
- **Document Service:** `/documents/**` → `http://document-service:8085`

### Bảo mật (Security)
- Kiểm tra JWT thông qua cấu hình `spring-boot-starter-oauth2-resource-server`.
- Sử dụng JWKS (JSON Web Key Set) từ Auth Service để xác thực chữ ký token.

### Rate Limiting (Giới hạn lưu lượng)
Sử dụng Redis để quản lý số lượng request cho từng API. Ví dụ:
- **User Service:** 5 request/giây (Replenish Rate) và 10 request tối đa (Burst Capacity).
- **Auth Service:** 10 request/giây và 20 request tối đa.

### Circuit Breaker (Ngắt mạch)
Mỗi service được bảo vệ bởi một Circuit Breaker thông qua Resilience4j. Khi một service gặp sự cố, Gateway sẽ chuyển tiếp yêu cầu đến một `fallbackUri` tương ứng (ví dụ: `/fallback/users`).

---

## 3. Cấu trúc thư mục
Dự án được tổ chức như sau:

```bash
api-gateway/
├── src/main/java/gateway/
│   ├── config/                 # Cấu hình Rate Limiter & Exception Handlers
│   │   ├── RateLimiterConfig.java
│   │   └── ExceptionHandlerConfig.java
│   ├── controller/             # Xử lý các fallback requests
│   ├── filter/                 # Các Global Filters (Logging, TraceID)
│   │   ├── LoggingFilter.java
│   │   ├── TraceIdRequestFilter.java
│   │   └── TraceIdResponseFilter.java
│   ├── security/               # Cấu hình bảo mật JWT & WebSecurity
│   │   ├── JwtConfig.java
│   │   └── SecurityConfig.java
│   ├── exception/              # Tùy chỉnh lỗi Gateway
│   ├── response/               # Định dạng response trả về
│   └── ApiGatewayApplication.java # Class chạy chính
└── src/main/resources/
    ├── application.yml         # Cấu hình định tuyến, Circuit Breaker, Redis
    └── .env                    # Biến môi trường (không commit file này)
```

---

## 4. Công nghệ sử dụng
- **Java 17**
- **Spring Boot 3.2.8**
- **Spring Cloud Gateway** - Framework chính cho Gateway
- **Spring Security (Reactive)** - Bảo mật API bằng OAuth2/JWT
- **Redis (Reactive)** - Hỗ trợ Rate Limiting
- **Resilience4j** - Cơ chế Circuit Breaker & Fallback
- **Micrometer Tracing/Brave** - Quản lý Distributed Tracing (TraceID)
- **Gradle** - Công cụ xây dựng project
- **Spring Dotenv** - Quản lý biến môi trường qua file `.env`

---

## 5. Cấu hình môi trường

Các biến môi trường cần được cấu hình trong file `.env` hoặc hệ thống:

**Server:**
- `SERVER_PORT`: Port của Gateway (Mặc định: 8080)
- `SERVER_ADDRESS`: Địa chỉ bind (Mặc định: 0.0.0.0)

**Microservices URI:**
- `USER_SERVICE_URI`, `AUTHENTICA_SERVICE_URI`, `NOTIFICATION_SERVICE_URI`, ...

**Redis:**
- `REDIS_HOST`: Địa chỉ Redis server (Mặc định: redis-service)
- `REDIS_PORT`: Port Redis (Mặc định: 6379)

**Bảo mật:**
- `AUTH_SERVICE_JWKS_URL`: URL để lấy JWK Set từ Auth Service (ví dụ: http://localhost:8081/.well-known/jwks.json)
- `AUTH_SERVICE_ISSUER`: Issuer URI của Auth Service

**Logging:**
- `LOG_GATEWAY`: Mức độ log cho Gateway (Default: DEBUG)
- `LOG_NETTY`: Mức độ log cho Netty (Default: DEBUG)
