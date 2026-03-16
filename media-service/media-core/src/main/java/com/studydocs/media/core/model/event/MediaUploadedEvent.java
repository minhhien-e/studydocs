package com.studydocs.media.core.model.event;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;
@Builder
public record MediaUploadedEvent(
    UUID mediaId,
    Instant occurredAt
) implements DomainEvent, InternalEvent {}
