package com.studydocs.media.core.publisher;

import com.studydocs.media.core.model.event.InternalEvent;

public interface InternalEventPublisher {
    void publish(InternalEvent event);
}
