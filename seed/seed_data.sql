-- =============================================================================
-- StudyDocs Database Seed Data Script
-- Useful for populating development and testing databases.
-- =============================================================================

-- 1. Seed Roles
INSERT INTO roles (id, name, description) VALUES
(1, 'ROLE_ADMIN', 'Administrator with full system privileges'),
(2, 'ROLE_USER', 'Standard registered student user')
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 2. Seed Universities
INSERT INTO universities (id, code, name, english_name, logo_url, created_at, updated_at) VALUES
(1, 'HUST', 'Đại học Bách khoa Hà Nội', 'Hanoi University of Science and Technology', 'https://upload.wikimedia.org/wikipedia/vi/a/a1/Logo_Hust.png', NOW(), NOW()),
(2, 'VNU', 'Đại học Quốc gia Hà Nội', 'Vietnam National University, Hanoi', 'https://vnu.edu.vn/upload/logo.png', NOW(), NOW()),
(3, 'NEU', 'Đại học Kinh tế Quốc dân', 'National Economics University', 'https://neu.edu.vn/logo.png', NOW(), NOW()),
(4, 'HCMUT', 'Đại học Bách khoa TP.HCM', 'Ho Chi Minh City University of Technology', 'https://hcmut.edu.vn/logo.png', NOW(), NOW())
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 3. Seed Faculties
INSERT INTO faculties (id, name, code, slug, description, university_id, is_active, created_at, updated_at) VALUES
(1, 'Trường CNTT & Truyền thông (SoICT)', 'SOICT', 'soict', 'Khoa Công nghệ Thông tin', 1, 1, NOW(), NOW()),
(2, 'Khoa Điện - Điện tử', 'SEEE', 'seee', 'Khoa Điện Điện tử', 1, 1, NOW(), NOW()),
(3, 'Trường Công nghệ Thông tin (VNU-UET)', 'UET', 'uet', 'Trường ĐH Công nghệ - ĐHQGHN', 2, 1, NOW(), NOW())
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 4. Seed Departments
INSERT INTO departments (id, name, code, slug, description, faculty_id, is_active, created_at, updated_at) VALUES
(1, 'Bộ môn Khoa học Máy tính', 'CS', 'cs', 'Bộ môn KHMT', 1, 1, NOW(), NOW()),
(2, 'Bộ môn Kỹ thuật Phần mềm', 'SE', 'se', 'Bộ môn KTPM', 1, 1, NOW(), NOW()),
(3, 'Bộ môn Hệ thống Thông tin', 'IS', 'is', 'Bộ môn HTTT', 1, 1, NOW(), NOW())
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 5. Seed Subjects
INSERT INTO subjects (id, code, name, university_id, created_at, updated_at) VALUES
(1, 'IT3040', 'Lập trình Java Nâng cao', 1, NOW(), NOW()),
(2, 'MI1110', 'Giải tích 1', 1, NOW(), NOW()),
(3, 'IT3011', 'Cấu trúc Dữ liệu & Giải thuật', 1, NOW(), NOW()),
(4, 'IT3090', 'Cơ sở Dữ liệu', 1, NOW(), NOW())
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 6. Seed Test Users (Password: 123456 -> BCrypt Hashed)
INSERT INTO users (id, email, password, full_name, username, avatar_url, bio, university_id, university_name, faculty_id, major, is_private, created_at, updated_at) VALUES
('user-admin-001', 'admin@studydocs.com', '$2a$10$E2UPv7arXnmjP2oJqN.nU.k5j1tU8ZgG6bH9J1Lq3V8xY2Zg1Lq3V', 'System Admin', 'admin', 'https://i.pravatar.cc/150?u=admin', 'System Administrator', 1, 'Đại học Bách khoa Hà Nội', 1, 'Computer Science', 0, NOW(), NOW()),
('user-student-001', 'student@studydocs.com', '$2a$10$E2UPv7arXnmjP2oJqN.nU.k5j1tU8ZgG6bH9J1Lq3V8xY2Zg1Lq3V', 'Nguyên Văn A', 'nguyenvana', 'https://i.pravatar.cc/150?u=student', 'Sinh viên K65 Bách Khoa', 1, 'Đại học Bách khoa Hà Nội', 1, 'Software Engineering', 0, NOW(), NOW())
ON DUPLICATE KEY UPDATE email=VALUES(email);

-- Assign User Roles
INSERT INTO user_roles (user_id, role_id) VALUES
('user-admin-001', 1),
('user-student-001', 2)
ON DUPLICATE KEY UPDATE role_id=VALUES(role_id);

-- 7. Seed Sample Documents
INSERT INTO documents (id, title, description, file_url, thumbnail_url, category, school, page_count, academic_year, user_id, subject_id, university_id, like_count, comment_count, download_count, created_at, updated_at) VALUES
('doc-sample-001', 'Giáo trình Lập trình Java Nâng cao 2024', 'Bộ tài liệu bài giảng và bài tập Java nâng cao đầy đủ nhất.', 'documents/sample_java.pdf', 'thumbnails/sample_java.jpg', 'Giáo trình', 'Đại học Bách khoa Hà Nội', 150, '2023-2024', 'user-student-001', 1, 1, 45, 12, 120, NOW(), NOW()),
('doc-sample-002', 'Đề thi & Đáp án Giải tích 1 các năm', 'Tổng hợp 10 đề thi Cuối kỳ Giải tích 1 kèm lời giải chi tiết.', 'documents/sample_calculus.pdf', 'thumbnails/sample_calculus.jpg', 'Đề thi', 'Đại học Bách khoa Hà Nội', 45, '2023-2024', 'user-student-001', 2, 1, 88, 23, 310, NOW(), NOW())
ON DUPLICATE KEY UPDATE title=VALUES(title);

-- 8. Seed Sample Reviews
INSERT INTO document_reviews (id, document_id, user_id, rating, comment, reaction_type, created_at, updated_at) VALUES
('review-001', 'doc-sample-001', 'user-admin-001', 5, 'Tài liệu rất hay và chi tiết, cám ơn bạn đã chia sẻ!', 'LIKE', NOW(), NOW())
ON DUPLICATE KEY UPDATE comment=VALUES(comment);

-- 9. Seed Sample Notifications
INSERT INTO notifications (id, user_id, title, content, type, is_read, is_deleted, received_at, created_at, updated_at) VALUES
('notif-001', 'user-student-001', 'Chào mừng bạn đến với StudyDocs!', 'Tài khoản của bạn đã tạo thành công. Hãy khám phá và chia sẻ tài liệu nhé.', 'SYSTEM', 0, 0, NOW(), NOW(), NOW())
ON DUPLICATE KEY UPDATE title=VALUES(title);
