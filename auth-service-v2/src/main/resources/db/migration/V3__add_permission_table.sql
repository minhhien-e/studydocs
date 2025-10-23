CREATE TABLE permissions (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(100) NOT NULL UNIQUE,
                             description TEXT,
                             resource VARCHAR(50) NOT NULL, -- Đối tượng có quyền(ví dụ: USER, ORDER, PRODUCT)
                             action VARCHAR(50) NOT NULL,   -- Action on resource (e.g., CREATE, READ, UPDATE, DELETE)
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                             INDEX idx_name (name),
                             INDEX idx_resource_action (resource, action)
)