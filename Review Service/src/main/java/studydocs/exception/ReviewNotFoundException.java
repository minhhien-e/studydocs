package studydocs.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewNotFoundException extends RuntimeException {

    private final int errorCode = 501;

    public ReviewNotFoundException(UUID id) {
        super("Review not found: " + id);
    }
}
