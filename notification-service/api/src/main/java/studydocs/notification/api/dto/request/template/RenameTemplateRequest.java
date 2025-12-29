package studydocs.notification.api.dto.request.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.UUID;
@Data
public class RenameTemplateRequest {
    @JsonIgnore
    private UUID templateId;
    private String newName;
}
