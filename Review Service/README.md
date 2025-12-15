# Review Service (StudyDocs)

**Port**: `9051`  
**Database**: MongoDB – `mongodb://localhost:27017/reviews`  
**Document Service URL**: `http://localhost:9050/documents`

### Mã lỗi (error code) 
| Code | Ý nghĩa                          |
|------|----------------------------------|
| 500  | Lỗi không xác định            |
| 501  | Review không tồn tại         |
| 502  | Validation request lỗi       |
| 503  | Document ID không tồn tại / document không hợp lệ       |
| 504  | Lỗi gọi remote service       |

### Chức năng đã hoàn thiện
- Tạo review mới (validate documentId tồn tại trước khi tạo)
- Lấy review theo ID
- Lấy tất cả review (phân trang)
- Lấy review theo documentId (phân trang + filter theo rating)
- Lấy review theo userId (phân trang)
- Tính điểm trung bình rating của document
- Cập nhật rating & comment
- Xóa review (soft delete)

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