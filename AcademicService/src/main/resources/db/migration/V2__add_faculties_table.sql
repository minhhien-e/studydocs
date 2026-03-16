CREATE TABLE faculties (
                           id CHAR(36) NOT NULL PRIMARY KEY,
                           university_id CHAR(36) NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           slug VARCHAR(100) NOT NULL,
                           description TEXT,
                           code VARCHAR(100),
                           is_active BOOLEAN DEFAULT TRUE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Mỗi khoa có slug duy nhất trong cùng 1 trường
                           UNIQUE KEY uq_faculty_university_slug (university_id, slug),

                           CONSTRAINT fk_faculty_university FOREIGN KEY (university_id) REFERENCES universities(id) ON DELETE CASCADE,

    --  Index kết hợp giúp lọc nhanh khoa theo trường và trạng thái
                           INDEX idx_faculty_university_active (university_id, is_active),

    --  Hỗ trợ tìm kiếm hoặc sắp xếp gần đây trong 1 trường
                           INDEX idx_faculty_university_created (university_id, created_at DESC),

    -- (Tùy chọn) Tìm nhanh khoa theo code trong trường
                           INDEX idx_faculty_university_code (university_id, code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
