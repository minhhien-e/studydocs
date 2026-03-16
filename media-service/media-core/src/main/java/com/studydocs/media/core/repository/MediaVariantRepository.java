package com.studydocs.media.core.repository;

import com.studydocs.media.core.model.entity.MediaVariant;
import java.util.UUID;

public interface MediaVariantRepository {
    MediaVariant findById(UUID id);
    MediaVariant save(MediaVariant variant);
}
