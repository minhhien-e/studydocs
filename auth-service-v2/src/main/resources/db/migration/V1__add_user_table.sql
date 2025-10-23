CREATE TABLE user (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       user_name VARCHAR(100),
                       password VARCHAR(255),
                       provider ENUM('FACEBOOK', 'GOOGLE', 'GITHUB') NULL,
                       provider_id VARCHAR(255),
                       status ENUM('ACTIVE', 'BAN', 'DELETE') NOT NULL DEFAULT 'ACTIVE',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                       INDEX idx_email (email),
                       INDEX idx_status (status),
                       INDEX idx_provider (provider, provider_id)
);
