-- Bảng oauth2_registered_client: Lưu thông tin về các client được đăng ký
CREATE TABLE oauth2_registered_client (
                                          id VARCHAR(100) NOT NULL,
                                          client_id VARCHAR(100) NOT NULL,
                                          client_id_issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                          client_secret VARCHAR(200) DEFAULT NULL,
                                          client_secret_expires_at TIMESTAMP DEFAULT NULL,
                                          client_name VARCHAR(200) NOT NULL,
                                          client_authentication_methods VARCHAR(1000) NOT NULL,
                                          authorization_grant_types VARCHAR(1000) NOT NULL,
                                          redirect_uris VARCHAR(1000) DEFAULT NULL,
                                          scopes VARCHAR(1000) NOT NULL,
                                          client_settings VARCHAR(2000) NOT NULL,
                                          token_settings VARCHAR(2000) NOT NULL,

                                          PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng oauth2_authorization: Lưu thông tin về các authorization đã cấp
CREATE TABLE oauth2_authorization (
                                      id VARCHAR(100) NOT NULL,
                                      registered_client_id VARCHAR(100) NOT NULL,
                                      principal_name VARCHAR(200) NOT NULL,
                                      authorization_grant_type VARCHAR(100) NOT NULL,
                                      authorized_scopes VARCHAR(1000) DEFAULT NULL,
                                      attributes TEXT DEFAULT NULL,
                                      state VARCHAR(500) DEFAULT NULL,
                                      authorization_code_value TEXT DEFAULT NULL,
                                      authorization_code_issued_at TIMESTAMP DEFAULT NULL,
                                      authorization_code_expires_at TIMESTAMP DEFAULT NULL,
                                      authorization_code_metadata TEXT DEFAULT NULL,
                                      access_token_value TEXT DEFAULT NULL,
                                      access_token_issued_at TIMESTAMP DEFAULT NULL,
                                      access_token_expires_at TIMESTAMP DEFAULT NULL,
                                      access_token_metadata TEXT DEFAULT NULL,
                                      access_token_type VARCHAR(100) DEFAULT NULL,
                                      access_token_scopes VARCHAR(1000) DEFAULT NULL,
                                      refresh_token_value TEXT DEFAULT NULL,
                                      refresh_token_issued_at TIMESTAMP DEFAULT NULL,
                                      refresh_token_expires_at TIMESTAMP DEFAULT NULL,
                                      refresh_token_metadata TEXT DEFAULT NULL,
                                      oidc_id_token_value TEXT DEFAULT NULL,
                                      oidc_id_token_issued_at TIMESTAMP DEFAULT NULL,
                                      oidc_id_token_expires_at TIMESTAMP DEFAULT NULL,
                                      oidc_id_token_metadata TEXT DEFAULT NULL,
                                      oidc_id_token_claims TEXT DEFAULT NULL,

                                      PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng oauth2_authorization_consent: Lưu thông tin về các consent (sự đồng ý) của user
CREATE TABLE oauth2_authorization_consent (
                                              registered_client_id VARCHAR(100) NOT NULL,
                                              principal_name VARCHAR(200) NOT NULL,
                                              authorities VARCHAR(1000) NOT NULL,

                                              PRIMARY KEY (registered_client_id, principal_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Thêm các indexes để tối ưu performance
CREATE INDEX idx_oauth2_authorization_client ON oauth2_authorization (registered_client_id);
CREATE INDEX idx_oauth2_authorization_principal ON oauth2_authorization (principal_name);
CREATE INDEX idx_oauth2_registered_client_cid ON oauth2_registered_client (client_id);
ALTER TABLE oauth2_registered_client
    ADD COLUMN post_logout_redirect_uris VARCHAR(1000);