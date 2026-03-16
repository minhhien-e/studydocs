CREATE TABLE subjects (
                        id CHAR(36) NOT NULL PRIMARY KEY,
                        department_id CHAR(36) NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        slug VARCHAR(100) NOT NULL,
                        description TEXT,
                        code VARCHAR(100),
                        is_active BOOLEAN DEFAULT TRUE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                        UNIQUE KEY uq_subject_department_slug (department_id, slug),

                        CONSTRAINT fk_subject_department FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE,

    --  Index lọc nhanh theo department + trạng thái
                        INDEX idx_subject_department_active (department_id, is_active),

    --  Index hỗ trợ sắp xếp hoặc phân trang trong 1 department
                        INDEX idx_subject_department_created (department_id, created_at DESC),

    --  Index giúp tra cứu nhanh ngành theo mã trong một department
                        INDEX idx_subject_department_code (department_id, code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
