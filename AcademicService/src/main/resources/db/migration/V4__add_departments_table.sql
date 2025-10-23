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

                             INDEX idx_department_name (name),
                             INDEX idx_department_is_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
