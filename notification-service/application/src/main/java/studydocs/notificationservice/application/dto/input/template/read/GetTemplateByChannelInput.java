package studydocs.notificationservice.application.dto.input.template.read;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTemplateByChannelInput {
    private String channel;
}
