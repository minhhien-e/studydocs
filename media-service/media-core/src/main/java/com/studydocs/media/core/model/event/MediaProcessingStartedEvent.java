package com.studydocs.media.core.model.event;

import java.time.Instant;
import java.util.UUID;

public record MediaProcessingStartedEvent(
    UUID mediaId,
    Instant occurredAt
) implements DomainEvent, InternalEvent {}
