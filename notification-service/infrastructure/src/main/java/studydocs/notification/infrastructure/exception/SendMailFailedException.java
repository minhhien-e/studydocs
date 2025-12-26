package studydocs.notification.infrastructure.exception;

import studydocs.notification.infrastructure.exception.base.InfrastructureException;

public class SendMailFailedException extends InfrastructureException {
    public SendMailFailedException(String message) {
        super("Failed to send email notification: "+message);
    }
}
