# Thư viện Bảo mật cho Resource Server

Thư viện này cung cấp cấu hình bảo mật chuẩn cho các Resource Server sử dụng OAuth2 (JWT).

## Cài đặt

### 1. Thêm kho chứa JitPack
Thêm đoạn này vào file `build.gradle` (hoặc `pom.xml`) của bạn:

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```

### 2. Thêm thư viện (Dependency)
Thay thế `TAG` bằng phiên bản release mới nhất (ví dụ: `v1.0.0`) hoặc mã hash của commit.

```gradle
dependencies {
    implementation 'com.github.vanhao:studydocs:TAG'
}
```

## Cấu hình

Thư viện sử dụng cơ chế **Auto-Configuration** của Spring Boot. Bạn chỉ cần cung cấp đường dẫn `JWK Set URI` của Auth Service trong file `application.yml`:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: http://auth-service/oauth2/jwks
```

## Tính năng

- **Chuỗi lọc bảo mật chuẩn (Standard Security Filter Chain)**: Tắt CSRF, kích hoạt chế độ Stateless session cho REST API.
- **Xác thực JWT**: Tự động xác minh token JWT sử dụng JWK Set URI được cấu hình.
- **Ánh xạ Quyền (Permission Mapping)**: Tự động trích xuất claim `permissions` từ JWT và chuyển đổi thành các authority dạng `SCOPE_permission`.
- **Ánh xạ Vai trò (Role Mapping)**: Trích xuất claim `roles` và chuyển đổi thành `ROLE_role`.
- **Phản hồi lỗi thống nhất**: Trả về định dạng JSON chuẩn cho các lỗi 401 (Unauthorized) và 403 (Forbidden).

## Tùy chỉnh (Advanced)

Thư viện được thiết kế linh hoạt. Nếu bạn cần thay đổi hành vi mặc định (ví dụ: dùng logic `JwtDecoder` riêng), bạn chỉ cần định nghĩa Bean đó trong ứng dụng của bạn. Thư viện sẽ tự động nhường quyền ưu tiên cho Bean của bạn.

```java
@Bean
public JwtDecoder jwtDecoder() {
    // Logic custom của bạn
    return NimbusJwtDecoder.withJwkSetUri("...").build();
}
```

## Cách dùng

Chỉ cần sử dụng `@PreAuthorize` trong các controller của bạn để phân quyền:

```java
@PreAuthorize("hasAuthority('SCOPE_university:read')")
@GetMapping
public List<University> getAll() {
    // ...
}
```
