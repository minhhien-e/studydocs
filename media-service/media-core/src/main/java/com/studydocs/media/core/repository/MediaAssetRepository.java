package com.studydocs.media.core.repository;

import com.studydocs.media.core.model.entity.MediaAsset;
import java.util.UUID;

import com.studydocs.media.core.model.enums.AssetState;
import java.time.LocalDateTime;
import java.util.List;

public interface MediaAssetRepository {
    MediaAsset findById(UUID id);
    MediaAsset save(MediaAsset mediaAsset);
    List<MediaAsset> findByStateAndCreatedAtBefore(AssetState state, LocalDateTime time);
    void delete(MediaAsset mediaAsset);
}
