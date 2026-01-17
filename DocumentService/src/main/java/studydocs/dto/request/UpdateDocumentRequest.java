package studydocs.dto.request;

//import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDocumentRequest {

//    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    private String description;

//    @Size(max = 50, message = "School year must not exceed 50 characters")
    private String schoolYear;
}
