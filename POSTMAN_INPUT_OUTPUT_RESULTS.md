# StudyDocs Backend API Test Report - Inputs & Outputs

**Thời gian chạy**: 8/15/2026, 7:23:30 PM
**Môi trường**: Docker Compose (MySQL 8.0 + Spring Boot Backend)
**Tổng kết**: 29/29 Requests Passed (100%)

---

### 1. Register New User
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/user/public/auth/register`

**Input Body**:
```json
{
  "email": "user_1786796606212@example.com",
  "password": "123456",
  "fullName": "Test Automation User",
  "username": "user_1786796606212",
  "universityId": 1
}
```
- **Response Status**: `200 OK` (139ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjN2U1ODZhYy0zMTIzLTQ0NzUtODdhZS00NTY2ZDA5ZDk2MjQiLCJlbWFpbCI6InVzZXJfMTc4Njc5NjYwNjIxMkBleGFtcGxlLmNvbSIsImlhdCI6MTc4Njc5NjYwNiwiZXhwIjoxNzg2ODgzMDA2fQ.9sXx2LYkvwb7MVQzaI6SakRk25Oedp71BrGzl12s3Z8",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjN2U1ODZhYy0zMTIzLTQ0NzUtODdhZS00NTY2ZDA5ZDk2MjQiLCJpYXQiOjE3ODY3OTY2MDYsImV4cCI6MTc4NzQwMTQwNn0.ihkb9DuB1VDlAKeOMAAzLogx99Ae3zD3nhcpCidj85E",
    "tokenType": "Bearer",
    "expiresIn": 86400,
    "user": {
      "id": "c7e586ac-3123-4475-87ae-4566d09d9624",
      "email": "user_1786796606212@example.com",
      "fullName": "Test Automation User",
      "username": "user_1786796606212",
      "avatarUrl": null,
      "bio": null,
      "universityId": 1,
      "universityName": null,
      "facultyId": null,
      "major": null,
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    }
  }
}
```

---

### 2. Login
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/user/public/auth/login`

**Input Body**:
```json
{
  "email": "user_1786796606212@example.com",
  "password": "123456"
}
```
- **Response Status**: `200 OK` (94ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjN2U1ODZhYy0zMTIzLTQ0NzUtODdhZS00NTY2ZDA5ZDk2MjQiLCJlbWFpbCI6InVzZXJfMTc4Njc5NjYwNjIxMkBleGFtcGxlLmNvbSIsImlhdCI6MTc4Njc5NjYwNiwiZXhwIjoxNzg2ODgzMDA2fQ.9sXx2LYkvwb7MVQzaI6SakRk25Oedp71BrGzl12s3Z8",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjN2U1ODZhYy0zMTIzLTQ0NzUtODdhZS00NTY2ZDA5ZDk2MjQiLCJpYXQiOjE3ODY3OTY2MDYsImV4cCI6MTc4NzQwMTQwNn0.ihkb9DuB1VDlAKeOMAAzLogx99Ae3zD3nhcpCidj85E",
    "tokenType": "Bearer",
    "expiresIn": 86400,
    "user": {
      "id": "c7e586ac-3123-4475-87ae-4566d09d9624",
      "email": "user_1786796606212@example.com",
      "fullName": "Test Automation User",
      "username": "user_1786796606212",
      "avatarUrl": null,
      "bio": null,
      "universityId": 1,
      "universityName": null,
      "facultyId": null,
      "major": null,
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    }
  }
}
```

---

### 3. Refresh Token
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/user/public/auth/refresh-token`

**Input Body**:
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjN2U1ODZhYy0zMTIzLTQ0NzUtODdhZS00NTY2ZDA5ZDk2MjQiLCJpYXQiOjE3ODY3OTY2MDYsImV4cCI6MTc4NzQwMTQwNn0.ihkb9DuB1VDlAKeOMAAzLogx99Ae3zD3nhcpCidj85E"
}
```
- **Response Status**: `200 OK` (15ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjN2U1ODZhYy0zMTIzLTQ0NzUtODdhZS00NTY2ZDA5ZDk2MjQiLCJlbWFpbCI6InVzZXJfMTc4Njc5NjYwNjIxMkBleGFtcGxlLmNvbSIsImlhdCI6MTc4Njc5NjYwNiwiZXhwIjoxNzg2ODgzMDA2fQ.9sXx2LYkvwb7MVQzaI6SakRk25Oedp71BrGzl12s3Z8",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjN2U1ODZhYy0zMTIzLTQ0NzUtODdhZS00NTY2ZDA5ZDk2MjQiLCJpYXQiOjE3ODY3OTY2MDYsImV4cCI6MTc4NzQwMTQwNn0.ihkb9DuB1VDlAKeOMAAzLogx99Ae3zD3nhcpCidj85E",
    "tokenType": "Bearer",
    "expiresIn": 86400,
    "user": {
      "id": "c7e586ac-3123-4475-87ae-4566d09d9624",
      "email": "user_1786796606212@example.com",
      "fullName": "Test Automation User",
      "username": "user_1786796606212",
      "avatarUrl": null,
      "bio": null,
      "universityId": 1,
      "universityName": null,
      "facultyId": null,
      "major": null,
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    }
  }
}
```

---

### 4. Forgot Password
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/user/public/auth/forgot-password`

**Input Body**:
```json
{
  "email": "user_1786796606212@example.com"
}
```
- **Response Status**: `200 OK` (6ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": "Password reset instructions sent to user_1786796606212@example.com"
}
```

---

### 5. Logout
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/user/public/auth/logout`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (5ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": "Logged out successfully"
}
```

---

### 6. Get Profile Me
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/user/users/me`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (10ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "id": "c7e586ac-3123-4475-87ae-4566d09d9624",
    "email": "user_1786796606212@example.com",
    "fullName": "Test Automation User",
    "username": "user_1786796606212",
    "avatarUrl": null,
    "bio": null,
    "universityId": 1,
    "universityName": null,
    "facultyId": null,
    "major": null,
    "isPrivate": false,
    "followerCount": 0,
    "followingCount": 0,
    "documentCount": 0
  }
}
```

---

### 7. Get All Users
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/user/users`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (16ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": "13fdbca4-620e-4a40-abe4-92e8487eea5c",
      "email": "testuser_$guid@example.com",
      "fullName": "Test Automation User",
      "username": "testuser_$guid",
      "avatarUrl": null,
      "bio": null,
      "universityId": 1,
      "universityName": null,
      "facultyId": null,
      "major": null,
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    },
    {
      "id": "3d7c996a-5a90-4c88-8328-4b0c185cab2d",
      "email": "user_1786796400819@example.com",
      "fullName": "Nguyen Van A Updated",
      "username": "user_1786796400819",
      "avatarUrl": null,
      "bio": "Student at HUST",
      "universityId": 1,
      "universityName": "HUST",
      "facultyId": null,
      "major": "Computer Science",
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    },
    {
      "id": "773edc49-ef7c-4175-bd7e-2f0f0b3bd550",
      "email": "user_1786796518947@example.com",
      "fullName": "Nguyen Van A Updated",
      "username": "user_1786796518947",
      "avatarUrl": null,
      "bio": "Student at HUST",
      "universityId": 1,
      "universityName": "HUST",
      "facultyId": null,
      "major": "Computer Science",
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    },
    {
      "id": "c7e586ac-3123-4475-87ae-4566d09d9624",
      "email": "user_1786796606212@example.com",
      "fullName": "Test Automation User",
      "username": "user_1786796606212",
      "avatarUrl": null,
      "bio": null,
      "universityId": 1,
      "universityName": null,
      "facultyId": null,
      "major": null,
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    },
    {
      "id": "f8ff6128-fd56-4c95-afad-9f6c61a3a235",
      "email": "user_1786796425617@example.com",
      "fullName": "Nguyen Van A Updated",
      "username": "user_1786796425617",
      "avatarUrl": null,
      "bio": "Student at HUST",
      "universityId": 1,
      "universityName": "HUST",
      "facultyId": null,
      "major": "Computer Science",
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    },
    {
      "id": "user-admin-001",
      "email": "admin@studydocs.com",
      "fullName": "System Admin",
      "username": "admin",
      "avatarUrl": "https://i.pravatar.cc/150?u=admin",
      "bio": "System Administrator",
      "universityId": 1,
      "universityName": "Đại học Bách khoa Hà Nội",
      "facultyId": 1,
      "major": "Computer Science",
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    },
    {
      "id": "user-student-001",
      "email": "student@studydocs.com",
      "fullName": "Nguyên Văn A",
      "username": "nguyenvana",
      "avatarUrl": "https://i.pravatar.cc/150?u=student",
      "bio": "Sinh viên K65 Bách Khoa",
      "universityId": 1,
      "universityName": "Đại học Bách khoa Hà Nội",
      "facultyId": 1,
      "major": "Software Engineering",
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    }
  ]
}
```

---

### 8. Get User By ID
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/user/users/c7e586ac-3123-4475-87ae-4566d09d9624`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (10ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "id": "c7e586ac-3123-4475-87ae-4566d09d9624",
    "email": "user_1786796606212@example.com",
    "fullName": "Test Automation User",
    "username": "user_1786796606212",
    "avatarUrl": null,
    "bio": null,
    "universityId": 1,
    "universityName": null,
    "facultyId": null,
    "major": null,
    "isPrivate": false,
    "followerCount": 0,
    "followingCount": 0,
    "documentCount": 0
  }
}
```

---

### 9. Update User Info
- **HTTP Method**: `PUT`
- **URL**: `http://localhost:8090/api/v1/user/users/c7e586ac-3123-4475-87ae-4566d09d9624/info`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`

**Input Body**:
```json
{
  "fullName": "Nguyen Van A Updated",
  "bio": "Student at HUST",
  "universityName": "HUST",
  "major": "Computer Science"
}
```
- **Response Status**: `200 OK` (20ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "id": "c7e586ac-3123-4475-87ae-4566d09d9624",
    "email": "user_1786796606212@example.com",
    "fullName": "Nguyen Van A Updated",
    "username": "user_1786796606212",
    "avatarUrl": null,
    "bio": "Student at HUST",
    "universityId": 1,
    "universityName": "HUST",
    "facultyId": null,
    "major": "Computer Science",
    "isPrivate": false,
    "followerCount": 0,
    "followingCount": 0,
    "documentCount": 0
  }
}
```

---

### 10. Get Universities
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/academics/universities`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (12ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": 1,
      "code": "HUST",
      "name": "Đại học Bách khoa Hà Nội",
      "englishName": "Hanoi University of Science and Technology",
      "logoUrl": "https://upload.wikimedia.org/wikipedia/vi/a/a1/Logo_Hust.png"
    },
    {
      "id": 2,
      "code": "VNU",
      "name": "Đại học Quốc gia Hà Nội",
      "englishName": "Vietnam National University, Hanoi",
      "logoUrl": "https://vnu.edu.vn/upload/logo.png"
    },
    {
      "id": 3,
      "code": "NEU",
      "name": "Đại học Kinh tế Quốc dân",
      "englishName": "National Economics University",
      "logoUrl": "https://neu.edu.vn/logo.png"
    },
    {
      "id": 4,
      "code": "HCMUT",
      "name": "Đại học Bách khoa TP.HCM",
      "englishName": "Ho Chi Minh City University of Technology",
      "logoUrl": "https://hcmut.edu.vn/logo.png"
    }
  ]
}
```

---

### 11. Filter Universities
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/academics/universities/filter?keyword=HUST`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (9ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": 1,
      "code": "HUST",
      "name": "Đại học Bách khoa Hà Nội",
      "englishName": "Hanoi University of Science and Technology",
      "logoUrl": "https://upload.wikimedia.org/wikipedia/vi/a/a1/Logo_Hust.png"
    }
  ]
}
```

---

### 12. Filter Faculties
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/academics/faculties/filter?universityId=1`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (8ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": 1,
      "code": "SOICT",
      "name": "Trường CNTT & Truyền thông (SoICT)",
      "slug": "soict",
      "description": "Khoa Công nghệ Thông tin",
      "universityId": 1,
      "isActive": true
    },
    {
      "id": 2,
      "code": "SEEE",
      "name": "Khoa Điện - Điện tử",
      "slug": "seee",
      "description": "Khoa Điện Điện tử",
      "universityId": 1,
      "isActive": true
    }
  ]
}
```

---

### 13. Filter Departments
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/academics/departments/filter?facultyId=1`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (8ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": 1,
      "code": "CS",
      "name": "Bộ môn Khoa học Máy tính",
      "slug": "cs",
      "description": "Bộ môn KHMT",
      "facultyId": 1,
      "isActive": true
    },
    {
      "id": 2,
      "code": "SE",
      "name": "Bộ môn Kỹ thuật Phần mềm",
      "slug": "se",
      "description": "Bộ môn KTPM",
      "facultyId": 1,
      "isActive": true
    },
    {
      "id": 3,
      "code": "IS",
      "name": "Bộ môn Hệ thống Thông tin",
      "slug": "is",
      "description": "Bộ môn HTTT",
      "facultyId": 1,
      "isActive": true
    }
  ]
}
```

---

### 14. Get Subjects
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/academics/subjects`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (8ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": 1,
      "code": "IT3040",
      "name": "Lập trình Java Nâng cao",
      "universityId": 1
    },
    {
      "id": 2,
      "code": "MI1110",
      "name": "Giải tích 1",
      "universityId": 1
    },
    {
      "id": 3,
      "code": "IT3011",
      "name": "Cấu trúc Dữ liệu & Giải thuật",
      "universityId": 1
    },
    {
      "id": 4,
      "code": "IT3090",
      "name": "Cơ sở Dữ liệu",
      "universityId": 1
    }
  ]
}
```

---

### 15. Get Most Liked Documents
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/documents/public/most-liked?limit=10`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (8ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": "doc-sample-002",
      "title": "Đề thi & Đáp án Giải tích 1 các năm",
      "thumbnail": "thumbnails/sample_calculus.jpg",
      "category": "Đề thi",
      "school": "Đại học Bách khoa Hà Nội",
      "pageCount": 45,
      "year": "2023-2024",
      "likeCount": 88,
      "commentCount": 23,
      "isLiked": false,
      "isBookmarked": false
    },
    {
      "id": "doc-sample-001",
      "title": "Giáo trình Lập trình Java Nâng cao 2024",
      "thumbnail": "thumbnails/sample_java.jpg",
      "category": "Giáo trình",
      "school": "Đại học Bách khoa Hà Nội",
      "pageCount": 150,
      "year": "2023-2024",
      "likeCount": 45,
      "commentCount": 12,
      "isLiked": false,
      "isBookmarked": false
    }
  ]
}
```

---

### 16. Get Newest Documents
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/documents/public/newest?limit=10`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (8ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": "doc-sample-001",
      "title": "Giáo trình Lập trình Java Nâng cao 2024",
      "thumbnail": "thumbnails/sample_java.jpg",
      "category": "Giáo trình",
      "school": "Đại học Bách khoa Hà Nội",
      "pageCount": 150,
      "year": "2023-2024",
      "likeCount": 45,
      "commentCount": 12,
      "isLiked": false,
      "isBookmarked": false
    },
    {
      "id": "doc-sample-002",
      "title": "Đề thi & Đáp án Giải tích 1 các năm",
      "thumbnail": "thumbnails/sample_calculus.jpg",
      "category": "Đề thi",
      "school": "Đại học Bách khoa Hà Nội",
      "pageCount": 45,
      "year": "2023-2024",
      "likeCount": 88,
      "commentCount": 23,
      "isLiked": false,
      "isBookmarked": false
    }
  ]
}
```

---

### 17. Search Documents
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/documents/search?q=math`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (8ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": []
}
```

---

### 18. Get My Documents
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/education/documents/user/me`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (11ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": []
}
```

---

### 19. Bookmark Document
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/education/documents/doc-sample-002/bookmark`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (5ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": "Document bookmarked"
}
```

---

### 20. Download Document
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/education/documents/doc-sample-002/download`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (18ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": "Download started"
}
```

---

### 21. Add/Update Document Review
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/user/reviews/document`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`

**Input Body**:
```json
{
  "documentId": "doc-sample-002",
  "rating": 5,
  "comment": "Very useful document!",
  "reactionType": "LIKE"
}
```
- **Response Status**: `200 OK` (17ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "id": "53316bb7-3d54-4fc7-8589-43079dcf97df",
    "documentId": "doc-sample-002",
    "userId": "c7e586ac-3123-4475-87ae-4566d09d9624",
    "rating": 5,
    "comment": "Very useful document!",
    "reactionType": "LIKE",
    "createdAt": "2026-08-15T12:23:27.014663091"
  }
}
```

---

### 22. Get Document Reviews
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/user/reviews/document?documentId=doc-sample-002`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (7ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": "30aea2b4-33a5-4bd8-a72b-e29b7f6eda95",
      "documentId": "doc-sample-002",
      "userId": "773edc49-ef7c-4175-bd7e-2f0f0b3bd550",
      "rating": 5,
      "comment": "Very useful document!",
      "reactionType": "LIKE",
      "createdAt": "2026-08-15T12:21:59.760807"
    },
    {
      "id": "4b09abfd-fc38-41d1-a745-85b51d77ce26",
      "documentId": "doc-sample-002",
      "userId": "3d7c996a-5a90-4c88-8328-4b0c185cab2d",
      "rating": 5,
      "comment": "Very useful document!",
      "reactionType": "LIKE",
      "createdAt": "2026-08-15T12:20:01.763338"
    },
    {
      "id": "53316bb7-3d54-4fc7-8589-43079dcf97df",
      "documentId": "doc-sample-002",
      "userId": "c7e586ac-3123-4475-87ae-4566d09d9624",
      "rating": 5,
      "comment": "Very useful document!",
      "reactionType": "LIKE",
      "createdAt": "2026-08-15T12:23:27.014663"
    },
    {
      "id": "f06155a7-9e5c-4cb6-8cca-cbdb6420bbae",
      "documentId": "doc-sample-002",
      "userId": "f8ff6128-fd56-4c95-afad-9f6c61a3a235",
      "rating": 5,
      "comment": "Very useful document!",
      "reactionType": "LIKE",
      "createdAt": "2026-08-15T12:20:26.419158"
    }
  ]
}
```

---

### 23. Get My Reaction Count
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/user/reviews/user/me/reactions/count`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (8ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "count": 1
  }
}
```

---

### 24. Follow User
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/user/follows/user-admin-001/follow`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (14ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": "User followed successfully"
}
```

---

### 25. Get My Followers
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/user/follows/followers`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (9ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": []
}
```

---

### 26. Get My Following
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/user/follows/following`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (10ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": [
    {
      "id": "user-admin-001",
      "email": "admin@studydocs.com",
      "fullName": "System Admin",
      "username": "admin",
      "avatarUrl": "https://i.pravatar.cc/150?u=admin",
      "bio": "System Administrator",
      "universityId": 1,
      "universityName": "Đại học Bách khoa Hà Nội",
      "facultyId": 1,
      "major": "Computer Science",
      "isPrivate": false,
      "followerCount": 0,
      "followingCount": 0,
      "documentCount": 0
    }
  ]
}
```

---

### 27. Init Media Upload
- **HTTP Method**: `POST`
- **URL**: `http://localhost:8090/api/v1/media/media/init-upload`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (6ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "mediaId": "temp-1786796607160",
    "fileName": null,
    "fileUrl": null,
    "contentType": null,
    "sizeBytes": null,
    "status": "INITIATED"
  }
}
```

---

### 28. Get Notifications
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/notifications`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (10ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": []
}
```

---

### 29. Get Unread Notification Count
- **HTTP Method**: `GET`
- **URL**: `http://localhost:8090/api/v1/notifications/unread-count`
- **Authorization**: `Bearer eyJhbGciOiJIUzI1NiJ9.ey...`
- **Input Body**: *(None)*
- **Response Status**: `200 OK` (9ms)

**Output Response Body**:
```json
{
  "statusCode": 200,
  "data": {
    "count": 0
  }
}
```

---

