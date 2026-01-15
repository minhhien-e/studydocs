package studydocs.media.application.port.in.job;

public interface OutboxEventHandler {
    boolean canHandle(String type);

    void handle(String payload);
}
