package studydocs.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateReviewRequest {

    @NotBlank
    private String comment;
}