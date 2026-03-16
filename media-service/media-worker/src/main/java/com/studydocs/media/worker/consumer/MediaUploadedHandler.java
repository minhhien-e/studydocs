package com.studydocs.media.worker.consumer;

import com.studydocs.media.core.model.event.MediaUploadedEvent;
import com.studydocs.media.worker.processing.MediaUploadedProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MediaUploadedHandler {
    private final MediaUploadedProcessor mediaUploadedProcessor;

    @EventListener
    public void handle(MediaUploadedEvent event) {
        mediaUploadedProcessor.process(event);
    }
}
