package studydocs.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewNotFoundException extends RuntimeException {

    private final int errorCode = 501;
    private final UUID reviewId;

    public ReviewNotFoundException(UUID reviewId) {
        super("Review not found with id: " + reviewId);
        this.reviewId = reviewId;
    }
}
