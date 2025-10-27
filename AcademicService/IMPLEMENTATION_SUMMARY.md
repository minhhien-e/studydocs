# Tổng kết Implementation - University và Faculty API

## 📁 Cấu trúc Code đã hoàn thiện

### 1. **Exception Layer** (`src/main/java/com/example/academicservice/exception/`)
```
exception/
├── ResourceNotFoundException.java       # Exception khi không tìm thấy resource
├── DuplicateResourceException.java      # Exception khi trùng lặp resource
└── GlobalExceptionHandler.java          # Xử lý tất cả exceptions globally
```

**Chức năng:**
- `ResourceNotFoundException`: Throw khi không tìm thấy University/Faculty
- `DuplicateResourceException`: Throw khi slug/code đã tồn tại
- `GlobalExceptionHandler`: Trả về response chuẩn với status code phù hợp

---

### 2. **Repository Layer** (`src/main/java/com/example/academicservice/repository/`)
```
repository/
├── UniversityRepository.java             # JPA repository cho University
└── FacultyRepository.java                # JPA repository cho Faculty
```

**UniversityRepository:**
- `findBySlug(String slug)`: Tìm university theo slug
- Các methods cơ bản từ JpaRepository (findAll, findById, save, delete...)

**FacultyRepository:**
- `findByUniversityId(Long)`: Lấy tất cả faculties của 1 university
- `findByUniversityIdAndIsActive(Long, Boolean)`: Lấy faculties active
- `findByUniversityIdAndSlug(Long, String)`: Tìm faculty theo slug trong university
- `existsByUniversityIdAndSlug(Long, String)`: Kiểm tra slug đã tồn tại

---

### 3. **DTOs** (`src/main/java/com/example/academicservice/dto/`)
```
dto/
├── request/
│   ├── UniversityCreateRequest.java     # DTO để tạo university
│   ├── FacultyCreateRequest.java        # DTO để tạo faculty
│   └── FacultyUpdateRequest.java        # DTO để update faculty
└── response/
    ├── UniversityResponse.java          # DTO response cho university
    └── FacultyResponse.java             # DTO response cho faculty
```

**Chức năng:**
- `CreateRequest`: Nhận dữ liệu từ client để tạo mới
- `UpdateRequest`: Nhận dữ liệu để cập nhật
- `Response`: Trả về cho client (không include sensitive data)

---

### 4. **Mapper Layer** (`src/main/java/com/example/academicservice/mapper/`)
```
mapper/
├── UniversityMapper.java                # MapStruct mapper cho University
└── FacultyMapper.java                   # MapStruct mapper cho Faculty
```

**Chức năng:**
- Chuyển đổi giữa Entity ↔ DTO
- MapStruct tự động generate code tại compile time

---

### 5. **Service Layer** (`src/main/java/com/example/academicservice/service/`)
```
service/
├── UniversityService.java               # Business logic cho University
└── FacultyService.java                  # Business logic cho Faculty
```

**UniversityService Methods:**
- ✅ `getAllUniversities()`: Lấy tất cả universities
- ✅ `getUniversityById(Long)`: Lấy theo ID
- ✅ `getUniversityBySlug(String)`: Lấy theo slug
- ✅ `createUniversity(UniversityCreateRequest)`: Tạo mới
- ✅ `updateUniversity(Long, UniversityCreateRequest)`: Cập nhật
- ✅ `deleteUniversityById(Long)`: Xóa theo ID
- ✅ `deleteUniversityBySlug(String)`: Xóa theo slug

**FacultyService Methods:**
- ✅ `getAllFacultiesByUniversityId(Long)`: Lấy tất cả faculties của 1 university
- ✅ `getActiveFacultiesByUniversityId(Long)`: Lấy faculties đang active
- ✅ `getFacultyById(Long)`: Lấy theo ID
- ✅ `getFacultyBySlug(Long, String)`: Lấy theo slug
- ✅ `createFaculty(FacultyCreateRequest)`: Tạo mới
- ✅ `updateFaculty(Long, FacultyUpdateRequest)`: Cập nhật
- ✅ `deleteFacultyById(Long)`: Xóa theo ID
- ✅ `deleteFacultyBySlug(Long, String)`: Xóa theo slug

---

### 6. **Controller Layer** (`src/main/java/com/example/academicservice/controller/`)
```
controller/
├── UniversityController.java            # REST API cho University
└── FacultyController.java              # REST API cho Faculty
```

**UniversityController Endpoints:**
```
GET    /api/v1/universities              # Lấy tất cả universities
GET    /api/v1/universities/id/{id}      # Lấy university theo ID
GET    /api/v1/universities/slug/{slug}  # Lấy university theo slug
POST   /api/v1/universities              # Tạo mới university
PUT    /api/v1/universities/{id}         # Cập nhật university
DELETE /api/v1/universities/id/{id}     # Xóa university theo ID
DELETE /api/v1/universities/slug/{slug} # Xóa university theo slug
```

**FacultyController Endpoints:**
```
GET    /api/v1/faculties/university/{universityId}              # Lấy tất cả faculties của university
GET    /api/v1/faculties/university/{universityId}/active      # Lấy faculties active
GET    /api/v1/faculties/id/{id}                              # Lấy faculty theo ID
GET    /api/v1/faculties/university/{universityId}/slug/{slug} # Lấy faculty theo slug
POST   /api/v1/faculties                                      # Tạo mới faculty
PUT    /api/v1/faculties/{id}                                 # Cập nhật faculty
DELETE /api/v1/faculties/id/{id}                              # Xóa faculty theo ID
DELETE /api/v1/faculties/university/{universityId}/slug/{slug} # Xóa faculty theo slug
```

---

## 🔄 Luồng hoạt động (Flow)

### 1. **Tạo University mới**
```
Client → POST /api/v1/universities
       ↓
Controller → validate & forward
       ↓
Service → generate slug, check duplicate, save
       ↓
Repository → save to DB
       ↓
Service → convert to Response DTO
       ↓
Controller → return Response
```

### 2. **Tạo Faculty mới**
```
Client → POST /api/v1/faculties
       ↓
Controller → validate & forward
       ↓
Service → check university exists, generate slug, check duplicate
       ↓
Repository → save to DB (with FK to university)
       ↓
Service → convert to Response DTO
       ↓
Controller → return Response
```

---

## 🎯 Các tính năng đã implement

### ✅ Business Logic
- Tự động generate slug từ name
- Kiểm tra duplicate slug trước khi tạo
- Validate university tồn tại trước khi tạo faculty
- Soft delete với is_active flag
- Transaction management (@Transactional)

### ✅ Exception Handling
- Custom exceptions với message rõ ràng
- Global exception handler trả về response chuẩn
- HTTP status codes phù hợp (404, 409, 500)

### ✅ Code Organization
- Comments tiếng Việt đầy đủ để dễ hiểu
- Separation of concerns (Controller → Service → Repository)
- DTOs tách biệt request/response
- MapStruct để giảm boilerplate code

---

## 🚀 Sử dụng API

### Example: Tạo University
```bash
POST http://localhost:8080/api/v1/universities
Content-Type: application/json

{
  "name": "Đại học Bách Khoa",
  "description": "Trường đại học kỹ thuật hàng đầu",
  "code": "BKU",
  "address": "268 Lý Thường Kiệt, Hà Nội",
  "phone": "0123456789",
  "email": "info@hust.edu.vn"
}
```

Response sẽ có `slug` tự động: `"slug": "dai-hoc-bach-khoa"`

### Example: Tạo Faculty
```bash
POST http://localhost:8080/api/v1/faculties
Content-Type: application/json

{
  "universityId": 1,
  "name": "Khoa Công nghệ thông tin",
  "description": "Đào tạo IT hàng đầu",
  "code": "IT"
}
```

---

## 📝 Lưu ý

1. **Slug generation**: Tự động từ name, unique trong scope của parent
2. **Foreign key**: Faculty phải thuộc về 1 University
3. **Cascade delete**: Xóa University sẽ xóa tất cả Faculties
4. **Logging**: Tất cả operations đều có log
5. **Transaction**: Service methods được đánh dấu @Transactional

---

## 🔜 Có thể mở rộng thêm

- [ ] Department API (tương tự Faculty)
- [ ] Major API (tương tự Faculty)
- [ ] Validation annotations trên DTOs
- [ ] Pagination cho GET all
- [ ] Search/Filter functionality
- [ ] Unit tests
- [ ] Integration tests

