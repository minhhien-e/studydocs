# Danh sách mã lỗi Academic Service

Tài liệu này mô tả các mã lỗi hệ thống, giá trị được quy định bằng giá trị gốc + 200.

| Mã lỗi | Giá trị | Mô tả |
| :--- | :--- | :--- |
| **NOT FOUND** | | |
| `UNIVERSITY_NOT_FOUND` | 201 | Không tìm thấy trường đại học. |
| `FACULTY_NOT_FOUND` | 202 | Không tìm thấy khoa. |
| `DEPARTMENT_NOT_FOUND` | 203 | Không tìm thấy bộ môn. |
| `MAJOR_NOT_FOUND` | 204 | Không tìm thấy ngành học. |
| **CONFLICT** | | |
| `UNIVERSITY_SLUG_EXISTS` | 205 | Slug trường đại học đã tồn tại (duplicate). |
| `FACULTY_SLUG_EXISTS` | 206 | Slug khoa đã tồn tại (duplicate). |
| `DEPARTMENT_SLUG_EXISTS` | 207 | Slug bộ môn đã tồn tại (duplicate). |
| `MAJOR_SLUG_EXISTS` | 208 | Slug ngành học đã tồn tại (duplicate). |
| **ID MISMATCH** | | |
| `UNIVERSITY_ID_MISMATCH` | 209 | University ID không khớp (resource không thuộc về university được chỉ định). |
| `FACULTY_ID_MISMATCH` | 210 | Faculty ID không khớp (resource không thuộc về faculty/university được chỉ định). |
| `DEPARTMENT_ID_MISMATCH` | 211 | Department ID không khớp (resource không thuộc về department/faculty/university được chỉ định). |
| `MAJOR_ID_MISMATCH` | 212 | Major ID không khớp (resource không thuộc về major/department/faculty/university được chỉ định). |
| **VALIDATION** | | |
| `INVALID_UUID` | 213 | UUID format không hợp lệ. |
| **UNKNOWN** | | |
| `UNKNOWN_ACADEMIC_ERROR` | 299 | Lỗi academic không xác định (fallback). |

## Mã lỗi chung (Common Error Codes)

Academic Service cũng sử dụng các mã lỗi chung từ `CommonErrorCodes`:

| Mã lỗi | Giá trị | Mô tả |
| :--- | :--- | :--- |
| `VALIDATION_FAILED` | 100 | Request validation thất bại (ví dụ: @Valid). |
| `BAD_REQUEST` | 101 | Bad request chung. |
| `ROLE_NOT_FOUND` | 102 | Không tìm thấy role. |
| `PERMISSION_NOT_FOUND` | 103 | Không tìm thấy permission. |
| `PERMISSION_ALREADY_EXISTS` | 104 | Permission đã tồn tại. |
| `INTERNAL_ERROR` | 500 | Lỗi server không xử lý được. |

## Mã lỗi xác thực (Auth Error Codes)

Academic Service sử dụng JWT authentication và có thể trả về các mã lỗi từ `AuthErrorCodes`:

| Mã lỗi | Giá trị | Mô tả |
| :--- | :--- | :--- |
| `ACCESS_TOKEN_INVALID_OR_EXPIRED` | 90 | Access token không hợp lệ hoặc đã hết hạn -> 401. |
| `FORBIDDEN` | 91 | Đã xác thực nhưng không có quyền -> 403. |
