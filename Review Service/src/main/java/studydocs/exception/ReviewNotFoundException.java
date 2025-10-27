package studydocs.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewNotFoundException extends RuntimeException {
    private final int errorCode;

    public ReviewNotFoundException(int errorCode, UUID id) {
        super("Không tìm thấy review có id: " + id);
        this.errorCode = errorCode;
    }

}