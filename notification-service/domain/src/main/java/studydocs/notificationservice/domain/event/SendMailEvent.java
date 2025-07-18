package studydocs.notificationservice.domain.event;

import studydocs.notificationservice.shared.enums.TemplateName;

import java.util.UUID;

public class SendMailEvent{
    private UUID userId;
    private String email;
    private TemplateName templateName;

    public SendMailEvent(UUID userId, String email, TemplateName templateName) {
        this.userId = userId;
        this.email = email;
        this.templateName = templateName;
    }

    public SendMailEvent() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TemplateName getTemplateName() {
        return templateName;
    }

    public void setTemplateName(TemplateName templateName) {
        this.templateName = templateName;
    }
}
