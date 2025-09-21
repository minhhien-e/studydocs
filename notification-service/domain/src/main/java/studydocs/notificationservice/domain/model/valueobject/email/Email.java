package studydocs.notificationservice.domain.model.valueobject.email;

public record Email(EmailAddress to, EmailSubject subject, EmailContent content) {
}
