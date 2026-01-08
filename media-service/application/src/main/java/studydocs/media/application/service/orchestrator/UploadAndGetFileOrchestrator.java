package studydocs.media.application.service.orchestrator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.UploadFileCommand;
import studydocs.media.application.dto.projection.FileProjection;
import studydocs.media.application.dto.query.GetFileByIdQuery;
import studydocs.media.application.port.in.usecase.GetFileByIdUseCasePort;
import studydocs.media.application.port.in.usecase.UploadFileUseCasePort;

@Service
@RequiredArgsConstructor
public class UploadAndGetFileOrchestrator {
    private final UploadFileUseCasePort uploadFileUseCase;
    private final GetFileByIdUseCasePort getFileByIdUseCasePort;
    public FileProjection uploadFile(UploadFileCommand command) {
        var fileId =uploadFileUseCase.execute(command);
        return getFileByIdUseCasePort.execute(new GetFileByIdQuery(fileId));
    }
}
