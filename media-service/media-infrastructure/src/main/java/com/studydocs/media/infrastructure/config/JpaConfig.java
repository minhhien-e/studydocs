package com.studydocs.media.infrastructure.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@Configuration
@EnableJpaRepositories(basePackages = "com.studydocs.media.infrastructure.repository")
@EntityScan(basePackages = "com.studydocs.media.core.model.entity")
public class JpaConfig {
}
