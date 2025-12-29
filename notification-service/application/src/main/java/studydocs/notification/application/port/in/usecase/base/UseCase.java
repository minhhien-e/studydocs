package studydocs.notification.application.port.in.usecase.base;

public interface UseCase<R, P> {
    R execute(P params);
}
