package com.studydocs.media.infrastructure.repository.jpa;

import com.studydocs.media.core.model.entity.MediaVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaVariantJpaRepository extends JpaRepository<MediaVariant, UUID> {
}
