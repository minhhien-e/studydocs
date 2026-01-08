package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.DeleteFileByIdCommand;
import studydocs.media.application.port.in.usecase.DeleteFileByIdUseCasePort;
import studydocs.media.domain.repository.FileWriter;

@Service
@RequiredArgsConstructor
public class DeleteFileByIdUseCase implements DeleteFileByIdUseCasePort {
    private final FileWriter fileWriter;
    @Override
    public Void execute(DeleteFileByIdCommand params) {
        var file = fileWriter.getById(params.fileId());
        file.delete();
        fileWriter.deleteById(params.fileId());
        return null;
    }
}
