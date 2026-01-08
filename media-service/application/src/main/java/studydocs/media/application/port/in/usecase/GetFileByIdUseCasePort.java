package studydocs.media.application.port.in.usecase;

import studydocs.media.application.dto.projection.FileProjection;
import studydocs.media.application.dto.query.GetFileByIdQuery;
import studydocs.media.application.port.in.usecase.base.UseCase;

public interface GetFileByIdUseCasePort extends UseCase<GetFileByIdQuery, FileProjection> {
}
