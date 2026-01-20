# User Service

## 1. Overview
The **User Service** is a core microservice in the StudyDocs ecosystem responsible for managing user accounts, authentication, profiles, and related operations. It provides a set of RESTful APIs that enable other services (e.g., document library, notification service) to retrieve and manipulate user data.

Key responsibilities include:
- **User registration & login** (JWT based authentication)
- **Profile management** (view, update, avatar upload)
- **User preferences** (notification settings, language, etc.)
- **Security** (password hashing, token validation, role‑based access control)

The service runs on **port `8080`** by default.

---

## 2. Detailed Features

### Account Management
- **Register** – Create a new user with email, password, and optional profile fields.
- **Login** – Authenticate credentials and issue a JWT token.
- **Refresh Token** – Issue a new access token using a refresh token.
- **Logout** – Invalidate refresh token.

### Profile Management
- **Get Profile** – Retrieve the current user's profile information.
- **Update Profile** – Modify fields such as `fullName`, `email`, `school`, `bio`, etc.
- **Upload Avatar** – Upload a profile picture (stored in the file‑storage service).

### Preferences & Settings
- **Notification Preferences** – Enable/disable email or push notifications per channel.
- **Language / Theme** – Store UI preferences.

### Security & Auditing
- **Password Reset** – Request a reset link and change password.
- **Account Deactivation** – Soft‑delete a user while retaining data for compliance.
- **Audit Logs** – Record critical actions (login, password change, profile update).

---

## 3. Directory Structure
```bash
user-service/
├── application/                # Use‑case layer (business logic)
│   ├── dto/                     # Data Transfer Objects
│   │   ├── input/               # Input DTOs for use‑cases
│   │   └── output/              # Output DTOs for responses
│   ├── port/                    # Port interfaces for external adapters
│   │   ├── auth/                # Authentication provider interface
│   │   └── storage/             # File‑storage (avatar) interface
│   └── service/                 # Service implementations (e.g., AuthService)
├── domain/                     # Domain layer (entities, value objects, rules)
│   ├── entity/                  # Core domain entities (User, Role, Preference)
│   ├── repository/              # Repository interfaces
│   └── valueobject/             # Value objects (Email, Password, AvatarUrl)
├── infrastructure/             # External adapters & framework code
│   ├── inbound/                 # Controllers, request handling
│   │   └── web/                 # REST API controllers
│   │       ├── dto/             # Request/Response DTOs and mappers
│   │       └── rest/            # Spring MVC controllers
│   └── outbound/                # Implementations of ports
│       ├── auth/                # JWT token generation/validation
│       ├── persistence/         # Spring Data MongoDB repositories
│       └── storage/             # Avatar file storage (e.g., AWS S3)
├── shared/                     # Common utilities & enums
│   ├── enums/                   # Enumerations (UserRole, NotificationChannel)
│   └── utils/                  # Helper classes (PasswordEncoder, JwtUtil)
└── resources/                  # Configuration files (application.yml, Dockerfile)
```

---

## 4. Technology Stack
- **Spring Boot** – Primary framework for building the microservice.
- **MongoDB** – Document‑oriented database for persisting user data.
- **Spring Security + JWT** – Authentication & authorization.
- **Spring Mail** – Email notifications (e.g., verification, password reset).
- **Docker** – Containerisation of the service.
- **Gradle** – Build tool.
- **Swagger/OpenAPI** – API documentation.

---

## 5. Environment Configuration
| Variable | Description |
|----------|-------------|
| `MONGO_HOST` | MongoDB host address |
| `MONGO_PORT` | MongoDB port |
| `MONGO_DB`   | Database name |
| `JWT_SECRET` | Secret key for signing JWT tokens |
| `JWT_EXPIRATION_MS` | Access token validity (ms) |
| `MAIL_HOST` | SMTP server host |
| `MAIL_PORT` | SMTP server port |
| `MAIL_USERNAME` | SMTP username |
| `MAIL_PASSWORD` | SMTP password |
| `STORAGE_PROVIDER` | Avatar storage provider (e.g., `s3`, `local`) |
| `STORAGE_BUCKET`   | Bucket name for avatar files |

All variables can be supplied via a `.env` file or Docker environment settings.

---

## 6. Running the Service
```bash
# Build the Docker image
docker build -t studydocs/user-service .

# Run with Docker Compose (example)
docker compose up -d user-service
```

The service will be reachable at `http://localhost:8080/api/v1/users`.

---

## 7. API Documentation
Swagger UI is available at `http://localhost:8080/swagger-ui.html` after the service starts.

---
## 8. Error Codes

The error codes are defined in the `ErrorCode` enum:

[ErrorCode.java](file:///d:/MyPropject/Microservice/studydocs/user-service/user-service/src/main/java/studydocs/user/error/ErrorCode.java)

| Code | ID | HTTP Status | Description |
|------|----|-------------|-------------|
| INTERNAL_SERVER_ERROR | -1 | 500 | Internal server error |
| BAD_REQUEST | 101 | 400 | Bad request |
| UNAUTHORIZED | 102 | 401 | Unauthorized |
| FORBIDDEN | 103 | 403 | Forbidden |
| NOT_FOUND | 104 | 404 | Not found |
| USER_NOT_FOUND | 110 | 404 | User not found |
| USER_ALREADY_EXISTS | 111 | 409 | User already exists |
| INVALID_USER_INPUT | 112 | 400 | Invalid user input |
| TOKEN_EXPIRED | 120 | 401 | Token expired |
| TOKEN_INVALID | 121 | 401 | Token invalid |
| OPERATION_NOT_ALLOWED | 130 | 403 | Operation not allowed |
| INVALID_RANGE | 131 | 400 | Invalid range |
| SAVE_FAILED | 140 | 500 | Save failed |
| UPDATE_FAILED | 141 | 500 | Update failed |
| DELETE_FAILED | 142 | 500 | Delete failed |
| NO_HANDLER | 150 | 204 | No handler |
| HTTP_FAIL | 160 | 417 | HTTP fail |

*This README was generated based on the structure and style of the Notification Service documentation, adapted for the User Service.*
