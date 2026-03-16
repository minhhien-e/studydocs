# Notification Service

## 1. Giới thiệu tổng quan
Đây là service thông báo trong hệ thống StudyDocs.
Nó đóng vai trò:
- **Gửi thông báo qua nhiều kênh** (Email, Push)
- **Quản lý mẫu thông báo** (Template) cho các loại thông báo khác nhau
- **Quản lý trạng thái thông báo**

> Service đang chạy tại **port `8081`**.

---

## 2. Chức năng chi tiết

### Quản lý thông báo
- **Tạo thông báo** → Tạo mới thông báo với template và dữ liệu động
- **Lấy thông báo theo người nhận** → Truy xuất danh sách thông báo với khả năng lọc theo thời gian và giới hạn
- **Đếm thông báo chưa đọc** → Đếm số lượng thông báo chưa đọc của người dùng
- **Đánh dấu đã đọc** → Đánh dấu một hoặc tất cả thông báo là đã đọc
- **Xóa mềm thông báo** → Đánh dấu thông báo là đã xóa nhưng vẫn lưu trong database
- **Xóa cứng thông báo** → Xóa vĩnh viễn thông báo khỏi database
- **Dọn dẹp tự động** → Tự động xóa thông báo cũ theo lịch trình (hàng ngày lúc 00:00)

### Quản lý mẫu thông báo (Template)
- **Tạo mẫu** → Khởi tạo mẫu thông báo mới với subject và body template
- **Lấy mẫu theo kênh** → Truy xuất tất cả mẫu của một kênh cụ thể (EMAIL, SMS, IN_APP, PUSH)
- **Lấy tất cả mẫu** → Truy xuất toàn bộ mẫu thông báo trong hệ thống
- **Tìm kiếm mẫu theo tên** → Tìm kiếm mẫu dựa trên tên mẫu
- **Cập nhật tên mẫu** → Thay đổi tên của mẫu hiện có
- **Cập nhật mô tả** → Cập nhật mô tả chi tiết cho mẫu
- **Cập nhật subject** → Cập nhật tiêu đề email của mẫu
- **Cập nhật body** → Cập nhật nội dung thông báo của mẫu

### Xử lý bất đồng bộ
- **Email Notification Listener** → Lắng nghe và xử lý sự kiện gửi email từ RabbitMQ
- **Upload Document Listener** → Lắng nghe sự kiện upload tài liệu và tạo thông báo push

### Các loại thông báo hỗ trợ
- **SYSTEM** → Thông báo hệ thống
- **NEW_DOCUMENT** → Thông báo tài liệu mới
- **REPLY_COMMENT** → Thông báo trả lời bình luận
- **LIKE_COMMENT** → Thông báo thích bình luận

### Các kênh thông báo hỗ trợ
- **EMAIL** → Gửi qua email
- **PUSH** → Thông báo đẩy (push notification)

---

## 3. Cấu trúc thư mục

```bash
notification-service/
├── application/                    # Lớp ứng dụng - chứa use cases và business logic
│   ├── dto/                       # Data Transfer Objects
│   │   ├── input/                 # Input DTOs cho các use cases
│   │   └── output/                # Output DTOs cho responses
│   ├── port/                      # Ports - interfaces cho external dependencies
│   │   ├── mail/                  # Email sending port
│   │   └── render/                # Template rendering port
│   ├── service/                   # Service implementations
│   │   ├── mail/                  # Email notification service
│   │   └── usecase/               # Use case implementations
│   └── usecase/                   # Use case interfaces
├── domain/                        # Lớp domain - chứa business entities và rules
│   ├── entity/                    # Domain entities
│   ├── event/                     # Domain events
│   ├── repository/                # Repository interfaces
│   ├── service/                   # Domain services
│   └── valueobject/               # Value objects
│       ├── date/                  # Date-related value objects
│       ├── notification/          # Notification-related value objects
│       └── template/              # Template-related value objects
├── infrastructure/                # Lớp hạ tầng - implementations và external integrations
│   ├── inbound/                   # Inbound adapters
│   │   ├── rabbitmq/              # RabbitMQ listeners
│   │   │   ├── mail/              # Email notification listener
│   │   │   └── notification/      # Document upload listener
│   │   ├── scheduling/            # Scheduled jobs
│   │   ├── security/              # Security configuration
│   │   ├── swagger/               # Swagger/OpenAPI documentation
│   │   └── web/                   # REST API controllers
│   │       ├── dto/               # Request/Response DTOs và mappers
│   │       └── rest/              # REST controllers
│   └── outbound/                  # Outbound adapters
│       ├── mail/                  # Email sending implementation
│       ├── persistence/           # Database persistence
│       │   ├── entity/            # JPA/MongoDB entities
│       │   ├── mapper/            # Entity mappers
│       │   └── repository/        # Repository implementations
│       ├── rabbitmq/              # RabbitMQ configuration
│       └── render/                # Template rendering implementation
├── shared/                        # Các thành phần dùng chung
│   ├── enums/                     # Enumerations
│   │   ├── NotificationChannel.java    # Kênh thông báo
│   │   └── NotificationType.java       # Loại thông báo
│   └── utils/                     # Utility classes
```

---

## 4. Công nghệ sử dụng

- **Spring Boot** - Framework chính
- **MongoDB** - Database chính
- **RabbitMQ** - Message broker cho xử lý bất đồng bộ
- **Spring Mail** - Gửi email
- **Swagger/OpenAPI** - API documentation
- **Docker** - Containerization
- **Gradle** - Build tool

---

## 5. Cấu hình môi trường

### Biến môi trường cần thiết:
- `MAIL_PORT` - Port SMTP
- `MAIL_USERNAME` - Username email
- `MAIL_PASSWORD` - Password email
- `RABBIT_HOST` - Host RabbitMQ
- `RABBIT_PORT` - Port RabbitMQ
- `RABBIT_USERNAME` - Username RabbitMQ
- `RABBIT_PASSWORD` - Password RabbitMQ
- `RABBIT_VIRTUAL_HOST` - Virtual host RabbitMQ
- `MONGO_HOST` - Host MongoDB
- `MONGO_PORT` - Port MongoDB
- `MONGO_DB` - Tên database MongoDB
