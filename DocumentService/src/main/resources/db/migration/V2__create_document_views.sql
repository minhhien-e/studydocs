CREATE TABLE IF NOT EXISTS document_views (
    id BINARY(16) NOT NULL,
    document_id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    viewed_at DATETIME(6),
    PRIMARY KEY (id),
    INDEX idx_view_user_id (user_id),
    INDEX idx_view_created_at (viewed_at)
) ENGINE=InnoDB;
