# API Endpoints - University & Faculty (Dual Approach)

## 🎯 Tổng quan

Project hiện tại hỗ trợ **CẢ HAI CÁCH** truy vấn:
- **ID-based**: Performance tốt, phù hợp internal/mobile
- **Slug-based**: User-friendly, phù hợp frontend/web

---

## 📋 University API Endpoints

### ID-based Endpoints (Existing)
```
GET    /api/v1/universities                    # Lấy tất cả universities
GET    /api/v1/universities/id/{id}            # Lấy university theo ID
GET    /api/v1/universities/slug/{slug}        # Lấy university theo slug
POST   /api/v1/universities                    # Tạo mới university
PUT    /api/v1/universities/{id}               # Cập nhật university theo ID
DELETE /api/v1/universities/id/{id}           # Xóa university theo ID
DELETE /api/v1/universities/slug/{slug}       # Xóa university theo slug
```

### Slug-based Endpoints (New)
```
GET    /api/v1/universities                    # Lấy tất cả universities
GET    /api/v1/universities/{slug}            # Lấy university theo slug
POST   /api/v1/universities                    # Tạo mới university
PUT    /api/v1/universities/{slug}            # Cập nhật university theo slug
DELETE /api/v1/universities/{slug}            # Xóa university theo slug
```

---

## 📋 Faculty API Endpoints

### ID-based Endpoints (Existing)
```
GET    /api/v1/faculties/university/{universityId}              # Lấy tất cả faculties của university
GET    /api/v1/faculties/university/{universityId}/active       # Lấy faculties active
GET    /api/v1/faculties/id/{id}                               # Lấy faculty theo ID
GET    /api/v1/faculties/university/{universityId}/slug/{slug} # Lấy faculty theo slug
POST   /api/v1/faculties                                       # Tạo mới faculty
PUT    /api/v1/faculties/{id}                                  # Cập nhật faculty theo ID
DELETE /api/v1/faculties/id/{id}                               # Xóa faculty theo ID
DELETE /api/v1/faculties/university/{universityId}/slug/{slug} # Xóa faculty theo slug
```

### Slug-based Endpoints (New)
```
GET    /api/v1/universities/{universitySlug}/faculties                    # Lấy tất cả faculties của university
GET    /api/v1/universities/{universitySlug}/faculties/active             # Lấy faculties active
GET    /api/v1/universities/{universitySlug}/faculties/{facultySlug}      # Lấy faculty theo slug
POST   /api/v1/universities/{universitySlug}/faculties                    # Tạo mới faculty
PUT    /api/v1/universities/{universitySlug}/faculties/{facultySlug}      # Cập nhật faculty theo slug
DELETE /api/v1/universities/{universitySlug}/faculties/{facultySlug}      # Xóa faculty theo slug
```

---

## 🔄 So sánh hai cách sử dụng

### ID-based (Performance-focused)
```bash
# Ví dụ: Lấy faculty "Khoa CNTT" của "ĐH Bách Khoa"
GET /api/v1/faculties/university/1/slug/khoa-cong-nghe-thong-tin

# Ưu điểm:
✅ Performance tốt (không cần JOIN)
✅ Phù hợp mobile app
✅ Internal microservices
✅ Batch processing
```

### Slug-based (User-friendly)
```bash
# Ví dụ: Lấy faculty "Khoa CNTT" của "ĐH Bách Khoa"
GET /api/v1/universities/dai-hoc-bach-khoa/faculties/khoa-cong-nghe-thong-tin

# Ưu điểm:
✅ URL đẹp, dễ đọc
✅ SEO-friendly
✅ Frontend web app
✅ User bookmark
✅ Public API documentation
```

---

## 📝 Ví dụ sử dụng

### 1. Tạo University
```bash
# Cả hai cách đều dùng cùng endpoint
POST /api/v1/universities
Content-Type: application/json

{
  "name": "Đại học Bách Khoa Hà Nội",
  "description": "Trường đại học kỹ thuật hàng đầu",
  "code": "BKU",
  "address": "268 Lý Thường Kiệt, Hà Nội",
  "phone": "0123456789",
  "email": "info@hust.edu.vn"
}

# Response sẽ có slug tự động: "dai-hoc-bach-khoa-ha-noi"
```

### 2. Tạo Faculty (ID-based)
```bash
POST /api/v1/faculties
Content-Type: application/json

{
  "universityId": 1,
  "name": "Khoa Công nghệ thông tin",
  "description": "Đào tạo IT hàng đầu",
  "code": "IT"
}
```

### 3. Tạo Faculty (Slug-based)
```bash
POST /api/v1/universities/dai-hoc-bach-khoa-ha-noi/faculties
Content-Type: application/json

{
  "name": "Khoa Công nghệ thông tin",
  "description": "Đào tạo IT hàng đầu",
  "code": "IT"
}
```

### 4. Lấy Faculty (ID-based)
```bash
GET /api/v1/faculties/university/1/slug/khoa-cong-nghe-thong-tin
```

### 5. Lấy Faculty (Slug-based)
```bash
GET /api/v1/universities/dai-hoc-bach-khoa-ha-noi/faculties/khoa-cong-nghe-thong-tin
```

---

## 🎯 Khi nào dùng cách nào?

### Dùng ID-based khi:
- 📱 Mobile applications
- ⚡ Performance-critical operations
- 🔧 Internal microservices
- 📊 Batch processing
- 🗄️ Database-heavy operations

### Dùng Slug-based khi:
- 🌐 Frontend web applications
- 🔍 SEO optimization
- 📖 Public API documentation
- 🔗 User bookmarking
- 📤 Sharing URLs

---

## 🚀 Lợi ích của Dual Approach

✅ **Flexibility**: Developer chọn cách phù hợp
✅ **Backward Compatibility**: Không phá vỡ API cũ
✅ **Performance Options**: ID cho speed, Slug cho UX
✅ **Future-proof**: Dễ mở rộng thêm features
✅ **Professional**: Giống các API lớn (GitHub, Stripe, AWS)

---

## 📚 Repository Methods

### FacultyRepository
```java
// ID-based queries
List<Faculty> findByUniversityId(Long universityId);
Optional<Faculty> findByUniversityIdAndSlug(Long universityId, String slug);

// Slug-based queries  
@Query("SELECT f FROM Faculty f JOIN f.university u WHERE u.slug = :universitySlug AND f.slug = :facultySlug")
Optional<Faculty> findByUniversitySlugAndFacultySlug(String universitySlug, String facultySlug);

@Query("SELECT f FROM Faculty f JOIN f.university u WHERE u.slug = :universitySlug")
List<Faculty> findByUniversitySlug(String universitySlug);
```

---

## 🎉 Kết luận

Project hiện tại đã hoàn thiện với **dual approach**:
- **ID-based**: Cho performance và internal use
- **Slug-based**: Cho user experience và public API

Đây là pattern được nhiều API lớn áp dụng và giúp API trở nên professional và flexible hơn rất nhiều!

