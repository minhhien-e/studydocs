CREATE TABLE subject_documents (
    id CHAR(36) NOT NULL PRIMARY KEY,
    subject_id CHAR(36) NOT NULL,
    document_id VARCHAR(255) NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_subject_document_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,

    INDEX idx_subject_document_subject (subject_id, is_active),
    INDEX idx_subject_document_created (subject_id, created_at DESC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
