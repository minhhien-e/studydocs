-- 5. User-Role mapping
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            created_by BIGINT NOT NULL, -- Người gán role

                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
                            FOREIGN KEY (created_by) REFERENCES user(id)
)