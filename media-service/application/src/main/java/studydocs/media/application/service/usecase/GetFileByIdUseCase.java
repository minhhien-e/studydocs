package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.projection.FileProjection;
import studydocs.media.application.dto.query.GetFileByIdQuery;
import studydocs.media.application.port.in.usecase.GetFileByIdUseCasePort;
import studydocs.media.application.port.out.repository.FileQueries;

@Service
@RequiredArgsConstructor
public class GetFileByIdUseCase implements GetFileByIdUseCasePort {
    private final FileQueries fileQueries;

    @Override
    public FileProjection execute(GetFileByIdQuery params) {
        return fileQueries.getById(params.id());
    }
}
