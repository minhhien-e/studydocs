CREATE TABLE follows (
    id CHAR(36) PRIMARY KEY,
    follower_id CHAR(36) NOT NULL,
    following_id CHAR(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_follower_following UNIQUE (follower_id, following_id)
);
