-- V1__Create_initial_tables.sql
-- InnoDB là storage engine mặc định của MySQL từ version 5.5 trở lên
-- utf8mb4_unicode_ci là collation mặc định cho MySQL 5.5.3 trở lên
-- COLLATE=utf8mb4_unicode_ci để hỗ trợ các ký tự Unicode

-- 1. Users table với các thông tin cơ bản (email, user_name, provider, status, created_at, updated_at)
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(255), -- NULL cho social login
    provider ENUM('FACEBOOK', 'GOOGLE', 'GITHUB') NULL, -- NULL cho local auth
    provider_id VARCHAR(255), -- ID từ OAuth provider
    status ENUM('ACTIVE', 'BAN', 'DELETE') NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_email (email),
    INDEX idx_status (status),
    INDEX idx_provider (provider, provider_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 2. Roles table (tách riêng để dễ mở rộng)
CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    is_system_role BOOLEAN DEFAULT FALSE, -- Đánh dấu role hệ thống
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Permissions table
-- Quản lý chi tiết quyền hạn:
CREATE TABLE permissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    resource VARCHAR(50) NOT NULL, -- Đối tượng có quyền(ví dụ: USER, ORDER, PRODUCT)
    action VARCHAR(50) NOT NULL,   -- Action on resource (e.g., CREATE, READ, UPDATE, DELETE)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_name (name),
    INDEX idx_resource_action (resource, action)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Role-Permission mapping (mapping giữa role và permission)

CREATE TABLE role_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. User-Role mapping
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL, -- Người gán role
    
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (created_by) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Ưu điểm của thiết kế này:
-- Linh hoạt:
-- Thêm/sửa/xóa role và permission dễ dàng
-- Có thể thêm metadata cho role/permission
-- Hỗ trợ phân quyền chi tiết
-- Khả năng mở rộng:
-- Phù hợp với hệ thống lớn (chắc vậy =))))
-- Dễ dàng thêm tính năng mới
-- Hỗ trợ nhiều loại permission


