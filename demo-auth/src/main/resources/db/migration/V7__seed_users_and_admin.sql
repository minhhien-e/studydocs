-- Thêm quyền WRITE_USER cho hệ thống
INSERT INTO permissions (id, permission_name, description)
VALUES ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'WRITE_USER', 'Allow create, update, delete users')
ON DUPLICATE KEY UPDATE permission_name = permission_name;

-- Thêm vai trò ROLE_ADMIN
INSERT INTO roles (id, role_name, description)
VALUES ('b2c3d4e5-f6a7-8901-2345-678901abcdef', 'ROLE_ADMIN', 'Administrator role with full permissions')
ON DUPLICATE KEY UPDATE role_name = role_name;

-- Gán quyền cho ROLE_ADMIN (Bao gồm cả READ_USER đã có ở V5 và WRITE_USER mới)
-- Giả định ID của READ_USER là 22222222-2222-2222-2222-222222222222 (từ V5)
INSERT INTO role_permissions (role_id, permission_id)
VALUES
    ('b2c3d4e5-f6a7-8901-2345-678901abcdef', '22222222-2222-2222-2222-222222222222'), -- ROLE_ADMIN có quyền READ_USER
    ('b2c3d4e5-f6a7-8901-2345-678901abcdef', 'a1b2c3d4-e5f6-7890-1234-567890abcdef')  -- ROLE_ADMIN có quyền WRITE_USER
ON DUPLICATE KEY UPDATE role_id = role_id;

-- Tạo tài khoản ADMIN (Password là: password)
-- Hash này tương ứng với chuỗi "password" (BCrypt)
INSERT INTO users (id, username, email, password_hash, display_name, is_active, email_verified)
VALUES
    ('c3d4e5f6-a7b8-9012-3456-789012abcdef', 'admin', 'admin@example.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 'System Admin', TRUE, TRUE)
ON DUPLICATE KEY UPDATE username = username;

-- Tạo tài khoản USER (Password là: password)
INSERT INTO users (id, username, email, password_hash, display_name, is_active, email_verified)
VALUES
    ('d4e5f6a7-b8c9-0123-4567-890123abcdef', 'user', 'user@example.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 'Normal User', TRUE, TRUE)
ON DUPLICATE KEY UPDATE username = username;

-- Gán Role cho các User vừa tạo
-- User Admin -> ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id)
VALUES ('c3d4e5f6-a7b8-9012-3456-789012abcdef', 'b2c3d4e5-f6a7-8901-2345-678901abcdef')
ON DUPLICATE KEY UPDATE user_id = user_id;

-- User thường -> ROLE_USER (ID của ROLE_USER lấy từ V5 là ...111)
INSERT INTO user_roles (user_id, role_id)
VALUES ('d4e5f6a7-b8c9-0123-4567-890123abcdef', '11111111-1111-1111-1111-111111111111')
ON DUPLICATE KEY UPDATE user_id = user_id;
