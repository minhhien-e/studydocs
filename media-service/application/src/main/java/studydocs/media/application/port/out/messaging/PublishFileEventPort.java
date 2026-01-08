package studydocs.media.application.port.out.messaging;


import studydocs.media.application.dto.payload.FileUploadedPayload;

public interface PublishFileEventPort {
    void publish(FileUploadedPayload event);
}

