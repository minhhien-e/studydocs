# StudyDocs Backend - Postman Test Execution Report

**Thời gian thực thi**: 2026-08-15
**Môi trường**: Docker Compose (MySQL 8.0 + Spring Boot Backend)
**Test Runner**: Newman CLI

---

## 📈 Tổng Quan Kết Quả (Summary)

| Chỉ số (Metric) | Đã thực thi (Executed) | Thất bại (Failed) | Tỷ lệ thành công (Pass Rate) |
| :--- | :---: | :---: | :---: |
| **Iterations** | 1 | 0 | **100%** |
| **Requests** | 29 | 0 | **100%** |
| **Test Scripts** | 29 | 0 | **100%** |
| **Prerequest Scripts** | 1 | 0 | **100%** |
| **Assertions** | 31 | 0 | **100%** |

- **Thời gian phản hồi trung bình (Average Response Time)**: `18ms` (Min: 5ms, Max: 146ms)
- **Tổng dung lượng nhận (Total Data Received)**: `10.96 kB`
- **Tổng thời gian chạy (Total Run Duration)**: `1057 ms`

---

## 📝 Chi Tiết Kết Quả Kiểm Thử Theo API Endpoint

### 1. Authentication Service
- `POST /api/v1/user/public/auth/register` - **[200 OK]** (146ms) - Status code is 200 or 201
- `POST /api/v1/user/public/auth/login` - **[200 OK]** (91ms) - Status code is 200 & Login returns token
- `POST /api/v1/user/public/auth/refresh-token` - **[200 OK]** (11ms) - Status code is 200
- `POST /api/v1/user/public/auth/forgot-password` - **[200 OK]** (5ms) - Status code is 200
- `POST /api/v1/user/public/auth/logout` - **[200 OK]** (5ms) - Status code is 200

### 2. User Profile Service
- `GET /api/v1/user/users/me` - **[200 OK]** (12ms) - Status code is 200 & Returns profile data
- `GET /api/v1/user/users` - **[200 OK]** (18ms) - Status code is 200
- `GET /api/v1/user/users/{userId}` - **[200 OK]** (13ms) - Status code is 200
- `PUT /api/v1/user/users/{userId}/info` - **[200 OK]** (22ms) - Status code is 200

### 3. Academic Catalog Service
- `GET /api/v1/education/academics/universities` - **[200 OK]** (11ms) - Status code is 200
- `GET /api/v1/education/academics/universities/filter?keyword=HUST` - **[200 OK]** (9ms) - Status code is 200
- `GET /api/v1/education/academics/faculties/filter?universityId=1` - **[200 OK]** (9ms) - Status code is 200
- `GET /api/v1/education/academics/departments/filter?facultyId=1` - **[200 OK]** (9ms) - Status code is 200
- `GET /api/v1/education/academics/subjects` - **[200 OK]** (8ms) - Status code is 200

### 4. Document Service
- `GET /api/v1/education/documents/public/most-liked?limit=10` - **[200 OK]** (6ms) - Status code is 200
- `GET /api/v1/education/documents/public/newest?limit=10` - **[200 OK]** (9ms) - Status code is 200
- `GET /api/v1/education/documents/search?q=math` - **[200 OK]** (11ms) - Status code is 200
- `GET /api/v1/education/documents/user/me` - **[200 OK]** (10ms) - Status code is 200
- `POST /api/v1/education/documents/{documentId}/bookmark` - **[200 OK]** (5ms) - Status code is 200
- `POST /api/v1/education/documents/{documentId}/download` - **[200 OK]** (14ms) - Status code is 200

### 5. Review Service
- `POST /api/v1/user/reviews/document` - **[200 OK]** (21ms) - Status code is 200
- `GET /api/v1/user/reviews/document?documentId={documentId}` - **[200 OK]** (8ms) - Status code is 200
- `GET /api/v1/user/reviews/user/me/reactions/count` - **[200 OK]** (8ms) - Status code is 200

### 6. Follow Service
- `POST /api/v1/user/follows/user-admin-001/follow` - **[200 OK]** (17ms) - Status code is 200
- `GET /api/v1/user/follows/followers` - **[200 OK]** (11ms) - Status code is 200
- `GET /api/v1/user/follows/following` - **[200 OK]** (11ms) - Status code is 200

### 7. System Service (Media & Notifications)
- `POST /api/v1/media/media/init-upload` - **[200 OK]** (6ms) - Status code is 200
- `GET /api/v1/notifications` - **[200 OK]** (10ms) - Status code is 200
- `GET /api/v1/notifications/unread-count` - **[200 OK]** (11ms) - Status code is 200

---

## 💻 Raw Newman CLI Output

```text
StudyDocs Backend API Collection

❏ 1. Authentication
↳ Register New User
  POST http://localhost:8090/api/v1/user/public/auth/register [200 OK, 1.25kB, 146ms]
  ✓  Status code is 200 or 201
↳ Login
  POST http://localhost:8090/api/v1/user/public/auth/login [200 OK, 1.25kB, 91ms]
  ✓  Status code is 200
  ✓  Login returns token
↳ Refresh Token
  POST http://localhost:8090/api/v1/user/public/auth/refresh-token [200 OK, 1.25kB, 11ms]
  ✓  Status code is 200
↳ Forgot Password
  POST http://localhost:8090/api/v1/user/public/auth/forgot-password [200 OK, 517B, 5ms]
  ✓  Status code is 200
↳ Logout
  POST http://localhost:8090/api/v1/user/public/auth/logout [200 OK, 474B, 5ms]
  ✓  Status code is 200

❏ 2. User Profile
↳ Get Profile Me
  GET http://localhost:8090/api/v1/user/users/me [200 OK, 771B, 12ms]
  ✓  Status code is 200
  ✓  Returns profile data
↳ Get All Users
  GET http://localhost:8090/api/v1/user/users [200 OK, 2.53kB, 18ms]
  ✓  Status code is 200
↳ Get User By ID
  GET http://localhost:8090/api/v1/user/users/773edc49-ef7c-4175-bd7e-2f0f0b3bd550 [200 OK, 771B, 13ms]
  ✓  Status code is 200
↳ Update User Info
  PUT http://localhost:8090/api/v1/user/users/773edc49-ef7c-4175-bd7e-2f0f0b3bd550/info [200 OK, 800B, 22ms]
  ✓  Status code is 200

❏ 3. Academic Catalog
↳ Get Universities
  GET http://localhost:8090/api/v1/education/academics/universities [200 OK, 1.13kB, 11ms]
  ✓  Status code is 200
↳ Filter Universities
  GET http://localhost:8090/api/v1/education/academics/universities/filter?keyword=HUST [200 OK, 648B, 9ms]
  ✓  Status code is 200
↳ Filter Faculties
  GET http://localhost:8090/api/v1/education/academics/faculties/filter?universityId=1 [200 OK, 766B, 9ms]
  ✓  Status code is 200
↳ Filter Departments
  GET http://localhost:8090/api/v1/education/academics/departments/filter?facultyId=1 [200 OK, 861B, 9ms]
  ✓  Status code is 200
↳ Get Subjects
  GET http://localhost:8090/api/v1/education/academics/subjects [200 OK, 759B, 8ms]
  ✓  Status code is 200

❏ 4. Document Service
↳ Get Most Liked Documents
  GET http://localhost:8090/api/v1/education/documents/public/most-liked?limit=10 [200 OK, 1.05kB, 6ms]
  ✓  Status code is 200
↳ Get Newest Documents
  GET http://localhost:8090/api/v1/education/documents/public/newest?limit=10 [200 OK, 1.05kB, 9ms]
  ✓  Status code is 200
↳ Search Documents
  GET http://localhost:8090/api/v1/education/documents/search?q=math [200 OK, 451B, 11ms]
  ✓  Status code is 200
↳ Get My Documents
  GET http://localhost:8090/api/v1/education/documents/user/me [200 OK, 451B, 10ms]
  ✓  Status code is 200
↳ Bookmark Document
  POST http://localhost:8090/api/v1/education/documents/doc-sample-002/bookmark [200 OK, 470B, 5ms]
  ✓  Status code is 200
↳ Download Document
  POST http://localhost:8090/api/v1/education/documents/doc-sample-002/download [200 OK, 467B, 14ms]
  ✓  Status code is 200

❏ 5. Review Service
↳ Add/Update Document Review
  POST http://localhost:8090/api/v1/user/reviews/document [200 OK, 683B, 21ms]
  ✓  Status code is 200
↳ Get Document Reviews
  GET http://localhost:8090/api/v1/user/reviews/document?documentId=doc-sample-002 [200 OK, 1.15kB, 8ms]
  ✓  Status code is 200
↳ Get My Reaction Count
  GET http://localhost:8090/api/v1/user/reviews/user/me/reactions/count [200 OK, 460B, 8ms]
  ✓  Status code is 200

❏ 6. Follow Service
↳ Follow User
  POST http://localhost:8090/api/v1/user/follows/user-admin-001/follow [200 OK, 477B, 17ms]
  ✓  Status code is 200
↳ Get My Followers
  GET http://localhost:8090/api/v1/user/follows/followers [200 OK, 451B, 11ms]
  ✓  Status code is 200
↳ Get My Following
  GET http://localhost:8090/api/v1/user/follows/following [200 OK, 810B, 11ms]
  ✓  Status code is 200

❏ 7. System Service (Media & Notifications)
↳ Init Media Upload
  POST http://localhost:8090/api/v1/media/media/init-upload [200 OK, 569B, 6ms]
  ✓  Status code is 200
↳ Get Notifications
  GET http://localhost:8090/api/v1/notifications [200 OK, 451B, 10ms]
  ✓  Status code is 200
↳ Get Unread Notification Count
  GET http://localhost:8090/api/v1/notifications/unread-count [200 OK, 460B, 11ms]
  ✓  Status code is 200

┌─────────────────────────┬───────────────────┬──────────────────┐
│                         │          executed │           failed │
├─────────────────────────┼───────────────────┼──────────────────┤
│              iterations │                 1 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│                requests │                29 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│            test-scripts │                29 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│      prerequest-scripts │                 1 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│              assertions │                31 │                0 │
├─────────────────────────┴───────────────────┴──────────────────┤
│ total run duration: 1057ms                                     │
├────────────────────────────────────────────────────────────────┤
│ total data received: 10.96kB (approx)                          │
├────────────────────────────────────────────────────────────────┤
│ average response time: 18ms [min: 5ms, max: 146ms, s.d.: 28ms] │
└────────────────────────────────────────────────────────────────┘
```
