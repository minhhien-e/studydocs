package com.studydocs.media.core.repository;

import com.studydocs.media.core.model.entity.MediaProcessingJob;
import com.studydocs.media.core.model.enums.ProcessingJobStatus;
import java.util.UUID;

public interface MediaProcessingJobRepository {
    MediaProcessingJob findById(UUID id);
    MediaProcessingJob save(MediaProcessingJob job);
    MediaProcessingJob findByStatusAndAssetId(ProcessingJobStatus status, UUID mediaId);
}
