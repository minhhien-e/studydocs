-- Tạo role manager
INSERT INTO roles (name, description, is_system_role) VALUES
('MANAGER', 'Department manager with specific permissions', TRUE);



-- Tạo permission cho role manager
INSERT INTO permissions (name, description, resource, action) VALUES
-- Quản lý user trong phòng ban
('USER_VIEW_ALL', 'View all users in department', 'USER', 'VIEW_ALL'),
('USER_MANAGE', 'Manage user status', 'USER', 'MANAGE'),
-- Quản lý tài liệu
('DOCUMENT_CREATE', 'Create new document', 'DOCUMENT', 'CREATE'),
('DOCUMENT_UPDATE', 'Update document', 'DOCUMENT', 'UPDATE'),
('DOCUMENT_DELETE', 'Delete document', 'DOCUMENT', 'DELETE'),
('DOCUMENT_APPROVE', 'Approve document', 'DOCUMENT', 'APPROVE'),
-- Quản lý báo cáo
('REPORT_VIEW', 'View department reports', 'REPORT', 'VIEW'),
('REPORT_GENERATE', 'Generate reports', 'REPORT', 'GENERATE');

-- Gán permission cho role manager
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
CROSS JOIN permissions p
WHERE r.name = 'MANAGER'
AND p.name IN (
    'USER_VIEW_ALL',
    'USER_MANAGE',
    'DOCUMENT_CREATE',
    'DOCUMENT_UPDATE',
    'DOCUMENT_DELETE',
    'DOCUMENT_APPROVE',
    'REPORT_VIEW',
    'REPORT_GENERATE'
);

-- Tạo user manager
INSERT INTO users (email, user_name, password, status) VALUES
('manager@example.com', 'Manager User', 'mot23@', 'ACTIVE');