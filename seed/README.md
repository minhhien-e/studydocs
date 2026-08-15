# StudyDocs Database Seed Data

Thư mục này chứa dữ liệu mẫu (Seed Data) chuẩn bị sẵn cho môi trường Development / Testing của **StudyDocs Backend**.

---

## 📂 Các File Trong Thư Mục

- **`seed_data.sql`**: File chứa câu lệnh SQL `INSERT` mẫu cho đầy đủ các bảng:
  - **Roles**: Admin, User
  - **Universities**: HUST, VNU, NEU, HCMUT
  - **Faculties & Departments**: Khoa SoICT, UET, Bộ môn KHMT, KTPM, HTTT
  - **Subjects**: Java Nâng cao, Giải tích 1, Cấu trúc Dữ liệu, Cơ sở Dữ liệu
  - **Users**: Admin (`admin@studydocs.com` / `123456`), Student (`student@studydocs.com` / `123456`)
  - **Documents**: Đề thi & Giáo trình mẫu
  - **Reviews & Notifications**: Đánh giá & Thông báo mẫu

---

## 🛠️ Hướng Dẫn Nạp Dữ Liệu Mẫu (Import Data)

### Cách 1: Nạp Trực Tiếp Qua MySQL CLI / MySQL Workbench
Mở Terminal và thực thi lệnh SQL:

```bash
mysql -u root -p studydocs < seed/seed_data.sql
```

### Cách 2: Tự Động Khởi Tạo Bằng DataInitializer Trong Spring Boot
Trong dự án Backend đã được tích hợp class `@Component DataInitializer`, tự động nạp dữ liệu mẫu khởi tạo (Roles & Universities) khi ứng dụng chạy nếu cơ sở dữ liệu đang trống.
