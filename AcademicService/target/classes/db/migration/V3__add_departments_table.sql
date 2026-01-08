CREATE TABLE departments (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             faculty_id BIGINT NOT NULL,
                             name VARCHAR(255) NOT NULL,
                             slug VARCHAR(100) NOT NULL,
                             description TEXT,
                             code VARCHAR(100),
                             is_active BOOLEAN DEFAULT TRUE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                             UNIQUE KEY uq_department_faculty_slug (faculty_id, slug),

                             FOREIGN KEY (faculty_id) REFERENCES faculties(id) ON DELETE CASCADE,

    --  Tối ưu lọc department theo khoa và trạng thái
                             INDEX idx_department_faculty_active (faculty_id, is_active),

    --  Tối ưu sắp xếp, phân trang theo thời gian trong 1 khoa
                             INDEX idx_department_faculty_created (faculty_id, created_at DESC),

    --  Tra cứu theo mã khoa bộ môn trong cùng khoa
                             INDEX idx_department_faculty_code (faculty_id, code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
