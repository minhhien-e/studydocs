package studydocs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReviewRequest {
    @NotNull(message = "Document ID không được null")
    private Long documentId;

    @NotNull(message = "User ID không được null")
    private Long userId;

    @NotNull(message = "Rating không được null")
    @Min(value = 1, message = "Rating phải từ 1-5")
    @Max(value = 5, message = "Rating phải từ 1-5")
    private Integer rating;

    @NotBlank(message = "Comment không được trống")
    private String comment;
}
