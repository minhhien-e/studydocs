package studydocs.service;

import studydocs.config.dto.ReviewCreatedPayload;

public interface NotificationService {
    void sendReviewCreated(ReviewCreatedPayload payload);

    void sendDocumentLiked(studydocs.config.dto.DocumentLikedPayload payload);

}
