package com.studydocs.media.infrastructure.repository.impl;

import com.studydocs.media.core.exception.MediaNotFoundException;
import com.studydocs.media.core.model.entity.MediaVariant;
import com.studydocs.media.core.repository.MediaVariantRepository;
import com.studydocs.media.infrastructure.repository.jpa.MediaVariantJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MediaVariantRepositoryImpl implements MediaVariantRepository {
    private final MediaVariantJpaRepository jpaRepository;

    @Override
    public MediaVariant findById(UUID id) {
        return jpaRepository.findById(id).orElseThrow(() -> new MediaNotFoundException(id.toString()));
    }

    @Override
    public MediaVariant save(MediaVariant variant) {
        return jpaRepository.save(variant);
    }
}
