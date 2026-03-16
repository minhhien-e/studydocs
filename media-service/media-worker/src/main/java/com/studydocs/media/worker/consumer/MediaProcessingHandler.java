package com.studydocs.media.worker.consumer;

import com.studydocs.media.core.model.event.MediaProcessingCompletedEvent;
import com.studydocs.media.core.model.event.MediaProcessingFailedEvent;
import com.studydocs.media.worker.processing.MediaProcessingProcessor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class MediaProcessingHandler {

    private final MediaProcessingProcessor mediaProcessingProcessor;

    @EventListener
    public void handleCompleted(MediaProcessingCompletedEvent event) {
        mediaProcessingProcessor.processCompleted(event);
    }

    @EventListener
    public void handleFailed(MediaProcessingFailedEvent event) {
        mediaProcessingProcessor.processFailed(event);
    }

}
