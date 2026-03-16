package com.studydocs.media.infrastructure.publisher;

import com.studydocs.media.core.model.event.InternalEvent;
import com.studydocs.media.core.publisher.InternalEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringInternalEventPublisher implements InternalEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(InternalEvent event) {
        publisher.publishEvent(event);
    }
}

