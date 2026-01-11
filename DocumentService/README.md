# Document Service (StudyDocs)

**Port**: `8085`
**Database**: MySQL – `jdbc:mysql://localhost:3306/document`
**Environment**: Standard Java Spring Boot

### Mã lỗi (Error Codes) - Range: 400-499
See `ERRORCODE.md`.

### API Endpoints
#### Public (`/api/v1/documents/public`)
- `GET /`: GetAll (List documents)
- `GET /{id}`: Detail (Records view if authenticated)
- `GET /{id}/exists`: Check if document exists
- `GET /newest?limit=10`: Top Newest Documents
- `GET /most-liked?limit=10`: Top Liked Documents (from ReviewService)

#### User (`/api/v1/documents/user`) - Requires Authentication
- `POST /`: Upload Document (multipart/form-data)
- `PUT /{id}`: Update Document (Title, Description)
- `DELETE /{id}`: Delete Document (Soft delete)
- `GET /me`: My Documents
- `GET /me/newest`: My Newest Documents
- `GET /me/history`: My View History

#### Admin (`/api/v1/documents/admin/stats`) - Requires Admin Role
- `GET /system?period={day,month,year}`: System Upload Stats
- `GET /total`: Total Documents Count
- `GET /users/{userId}/documents`: User Upload Stats
- `GET /users/{userId}/total`: User Total Uploads
- `GET /users/{userId}/likes-received`: Total Likes User Received
| Code | Message | Mô tả chi tiết (Description) |
|------|---------|------------------------------|
| -1   | Unknown Error | Lỗi hệ thống không xác định (Internal Server Error). |
| 400  | Bad Request | Dữ liệu yêu cầu không hợp lệ (chung). |
| 401  | Document Not Found | Không tìm thấy tài liệu với ID dã cung cấp. |
| 402  | Invalid File Type | Định dạng file không được hỗ trợ (chỉ chấp nhận PDF, DOCX, etc.). |
| 403  | File Size Exceeded | Kích thước file vượt quá giới hạn cho phép. |
| 404  | User Not Found | Không tìm thấy thông tin người dùng upload. |
| 405  | Invalid Title | Tiêu đề tài liệu bị thiếu hoặc không hợp lệ. |
| 406  | Permission Denied | Không có quyền chỉnh sửa/xóa tài liệu này. |
| 407  | Remote Upload Failed | Lỗi khi upload file sang UploadService. |


### Chức năng đã hoàn thiện
- Upload tài liệu (lưu file + metadata) - Kèm call Academic Service
- Lấy thông tin tài liệu theo ID (Public)
- Lấy danh sách tài liệu (phân trang + sort) (Public)
- Lấy tài liệu theo User ID (User)
- Cập nhật thông tin tài liệu (Title, Desc) (User)
- Xóa tài liệu (Soft delete) (User)
- Admin Stats: Thống kê upload theo ngày, tháng, user
- **New Features**:
    - Thêm trường School Year
    - Get Newest Documents (Public)
    - Get Most Liked Documents (Public - Call ReviewService)
    - View History (User)
    - Record View (Public/User)

### Cấu trúc thư mục
```
studydocs/
├── application/                   – Service / UseCase implementation
├── domain/                        – Entity & Domain logic
├── dto/
│   ├── request/                   – Upload / Update request
│   └── response/                  – ApiResponse
├── exception/                     – Custom exceptions
├── infrastructure/                – Repository impl, External adapters
├── interfaces/                    – Controller (API endpoint)
└── DocumentServiceApplication.java
```

### Thư viện / công nghệ chính
- Spring Boot 3.x
- Spring Data JPA (MySQL)
- Spring Security + OAuth2 Resource Server (JWT)
- Lombok
- Dockerize
