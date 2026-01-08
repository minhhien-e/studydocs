package studydocs.media.infrastructure.adapter.bus.handler;

import org.springframework.stereotype.Component;
import studydocs.media.application.dto.projection.FileProjection;
import studydocs.media.application.dto.query.GetFileByIdQuery;
import studydocs.media.application.port.in.usecase.GetFileByIdUseCasePort;
import studydocs.media.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class GetFileByIdHandler extends AbstractHandler<GetFileByIdQuery, FileProjection> {
    protected GetFileByIdHandler(GetFileByIdUseCasePort useCase) {
        super(useCase, GetFileByIdQuery.class);
    }
}
