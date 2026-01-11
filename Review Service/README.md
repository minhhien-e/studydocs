# Review Service (StudyDocs)

**Port**: `8086`  
**Database**: MongoDB – `mongodb://localhost:27017/reviews`  
**Document Service URL**: `http://localhost:8085/api/v1/documents`

### Mã lỗi (Error Codes) - Range: 500-599
| Code | Message | Mô tả chi tiết (Description) |
|------|---------|------------------------------|
| -1   | Unknown Error | Lỗi hệ thống không xác định (Internal Server Error). |
| 500  | Bad Request | Dữ liệu đầu vào không hợp lệ (Validation failed). |
| 501  | Review Not Found | Không tìm thấy bài đánh giá với ID cung cấp. |
| 502  | Invalid Document ID | Document ID không tồn tại (xác thực qua DocumentService). |
| 503  | Invalid Rating | Điểm đánh giá (rating) không hợp lệ (phải từ 1-5). |
| 504  | Remote Service Error | Lỗi kết nối đến DocumentService/AuthService. |
| 505  | Duplicate Review | Người dùng đã đánh giá tài liệu này rồi. |
| 506  | Content Too Short | Nội dung đánh giá quá ngắn (tối thiểu 10 ký tự). |
| 507  | User Not Authorized | User chưa đăng nhập hoặc Token hết hạn. |
| 508  | Permission Denied | Không có quyền sửa/xóa bài đánh giá của người khác. |


### Chức năng và Endpoints
#### Public/User (`/api/v1/reviews`)
- `POST /`: Create Review
- `GET /{id}`: Get Review By ID
- `GET /`: Get All Reviews
- `GET /document/{docId}`: Get Reviews by Document
- `GET /user/{userId}`: Get Reviews by User
- `PUT /{id}`: Update Review
- `DELETE /{id}`: Delete Review
- `POST /{id}/react`: React to Review (like/dislike)
- `POST /document/{docId}/react`: React to Document
- `GET /document/{docId}/stats`: Get Document Like/Dislike Stats
- `GET /document/{docId}/reaction`: Get My Reaction
- `GET /user/{userId}/count`: **[NEW]** Total Reviews by User (Requires Auth)

#### Internal (`/api/v1/internal/reactions`) - For Inter-service communication
- `POST /count-batch`: Count likes for list of documents
- `GET /top-liked`: **[NEW]** Get Top Liked Documents

#### Admin (`/api/v1/reviews/admin/stats`)
- `PUT /{id}/hidden`: Hide/Unhide Review
- `GET /reviews/total`: Total Reviews
- `GET /reactions/total-likes`: Total Document Likes

### Cấu trúc thư mục
```
studydocs/
├── client/                        - Client gọi documentservice 
│          
│        
├── config/                        – OAuth2 Resource Server + Jwt config + RestTemplate bean + MongoTemplate bean
│                                 
│            
│            
├── controller/                    – API endpoint
├── dto/
│   ├── request/                   – Create / Update request
│   └── response/                  – ApiResponse
├── exception/                     – Custom exceptions 
├── model/                         – Review entity 
├── repository/                    – ReviewRepository (Spring Data)
├── service/                       - Xử lí business logic
│   
│   
└── ReviewServiceApplication.java  - run (bsung yaml)
```

### Thư viện / công nghệ chính
- Spring Boot 3.x
- Spring Data MongoDB
- Spring Security + OAuth2 Resource Server (JWT)
- Spring Web + RestTemplate
- Lombok
- Jakarta Validation