package studydocs.media.application.port.in.usecase.base;

import studydocs.media.application.dto.base.Request;

public interface UseCase<P extends Request<R>, R> {
    R execute(P params);
}
