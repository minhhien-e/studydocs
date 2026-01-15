package studydocs.media.infrastructure.adapter.bus.handler.base;


import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import studydocs.media.application.dto.base.Request;
import studydocs.media.application.port.in.usecase.base.UseCase;

public abstract class AbstractHandler<P extends Request<R>, R> implements RequestHandler<BusRequestWrapper<P>, R> {
    protected UseCase<P, R> useCase;
    private final Class<?> requestClass;
    protected  AbstractHandler(UseCase<P, R> useCase, Class<?> requestClass) {
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
