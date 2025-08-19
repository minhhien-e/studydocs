package studydocs.dto;

import lombok.Data;

@Data
public class UploadDocumentRequest {
    private Long userId;
    private String title;
    private String description;
}