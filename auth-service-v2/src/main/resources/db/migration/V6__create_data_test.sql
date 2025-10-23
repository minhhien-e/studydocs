-- Tạo role manager
INSERT INTO roles (name, description, is_system_role) VALUES
    ('NORMAL', 'Nguoi dung ung dung', FALSE);



-- Tạo permission cho role manager
INSERT INTO permissions (name, description, resource, action) VALUES
-- Quản lý tài liệu
('DOCUMENT_CREATE', 'Create new document', 'NORMAL', 'CREATE'),
('DOCUMENT_UPDATE', 'Update document', 'NORMAL', 'UPDATE'),
('DOCUMENT_DELETE', 'Delete document', 'NORMAL', 'DELETE');

-- Gán permission cho role ACTIVE
INSERT INTO role_permissions (role_id, permission_id) VALUES
    ((SELECT id FROM roles WHERE name = 'NORMAL'), (SELECT id FROM permissions WHERE name = 'DOCUMENT_CREATE')),
    ((SELECT id FROM roles WHERE name = 'NORMAL'), (SELECT id FROM permissions WHERE name = 'DOCUMENT_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'NORMAL'), (SELECT id FROM permissions WHERE name = 'DOCUMENT_DELETE'));

-- Tạo user manager
INSERT INTO user (email, user_name, password, status) VALUES
    ('user1@gmail.com', 'Manager User', 'mot23@', 'ACTIVE');