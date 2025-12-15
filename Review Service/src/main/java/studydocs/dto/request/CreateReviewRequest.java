package studydocs.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateReviewRequest {

    @NotNull
    private UUID documentId;

    @NotNull
    private UUID userId;

    @NotBlank
    private String comment;
}
