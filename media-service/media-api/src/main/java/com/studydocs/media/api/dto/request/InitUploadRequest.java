package com.studydocs.media.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studydocs.media.core.model.enums.OwnerType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InitUploadRequest {
    private String contentType;
    private String fileName;
    private long sizeBytes;
    private OwnerType ownerType;
    private UUID ownerId;
    @JsonIgnore
    private String idempotencyKey;
}
