CREATE TABLE documents (
    id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    school_year VARCHAR(255),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    deleted_at DATETIME(6),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE document_views (
    id BINARY(16) NOT NULL,
    document_id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    viewed_at DATETIME(6),
    PRIMARY KEY (id),
    INDEX idx_view_user_id (user_id),
    INDEX idx_view_created_at (viewed_at)
) ENGINE=InnoDB;
