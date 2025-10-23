CREATE TABLE faculties (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           university_id BIGINT NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           slug VARCHAR(100) NOT NULL,
                           description TEXT,
                           code VARCHAR(100),
                           is_active BOOLEAN DEFAULT TRUE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                           UNIQUE KEY uq_faculty_university_slug (university_id, slug),
                           FOREIGN KEY (university_id) REFERENCES universities(id) ON DELETE CASCADE,

                           INDEX idx_faculty_name (name),
                           INDEX idx_faculty_is_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
