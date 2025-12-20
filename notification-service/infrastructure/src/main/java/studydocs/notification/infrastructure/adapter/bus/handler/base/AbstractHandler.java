package studydocs.notification.infrastructure.adapter.bus.handler.base;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public abstract class AbstractHandler<P, R> implements RequestHandler<BusRequestWrapper<P>, R> {
    protected UseCase<R, P> useCase;
    private final Class<?> requestClass;
    protected  AbstractHandler(UseCase<R, P> useCase, Class<?> requestClass) {
        this.useCase = useCase;
        this.requestClass = requestClass;
    }

    @Override
    public R execute(BusRequestWrapper<P> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return requestClass;
    }
}
