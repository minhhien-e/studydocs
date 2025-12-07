package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.template.InvalidTemplateChannelException;

public record TemplateChannel(String value) {
    public TemplateChannel {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateChannelException();
        }
    }
}
