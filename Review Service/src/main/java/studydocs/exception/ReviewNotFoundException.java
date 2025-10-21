package studydocs.exception;

import java.util.UUID;  // Import UUID

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(UUID id) {  // Nhận UUID object
        super("Không tìm thấy review có id: " + id);
    }
}