CREATE TABLE universities (
                              id CHAR(36) NOT NULL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL UNIQUE,
                              slug VARCHAR(100) NOT NULL UNIQUE,
                              description TEXT,
                              code VARCHAR(100) NOT NULL UNIQUE,
                              address VARCHAR(255),
                              phone VARCHAR(50),
                              email VARCHAR(100),
                              is_active BOOLEAN DEFAULT TRUE,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                              INDEX idx_universities_code (code),#xem lại chỉ mục nào hợp lý cho việc truy vấn
                              INDEX idx_universities_name (name),
                              INDEX idx_universities_is_active (is_active),
                              INDEX idx_universities_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
