package studydocs.notification.infrastructure.exception.base;

public abstract class InfrastructureException extends RuntimeException {

    public InfrastructureException(String message) {
        super(message);
    }
}
