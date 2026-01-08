package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.UploadFileCommand;
import studydocs.media.application.helper.PageCounterResolver;
import studydocs.media.application.port.in.usecase.UploadFileUseCasePort;
import studydocs.media.application.port.out.storage.FileStoragePort;
import studydocs.media.domain.aggregate.File;
import studydocs.media.domain.policy.FileSupportPolicy;
import studydocs.media.domain.repository.FileWriter;
import studydocs.media.domain.vo.FileName;
import studydocs.media.domain.vo.FileSize;
import studydocs.media.domain.vo.TotalPages;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadFileUseCase implements UploadFileUseCasePort {
    private final FileWriter fileWriter;
    private final PageCounterResolver pageCounterResolver;
    private final FileSupportPolicy fileSupportPolicy;
    private final FileStoragePort fileStoragePort;

    @Override
    public UUID execute(UploadFileCommand params) {
        var fileExt = fileSupportPolicy.supports(params.fileName());
        try {
            var totalPages = pageCounterResolver.countPages(fileExt, params.fileContent().getInputStream());
            var file = File.create(
                    params.uploaderId(),
                    FileName.of(params.fileName()),
                    FileSize.of(params.fileSize()),
                    TotalPages.of(totalPages)
            );
            var domainEvents = file.getDomainEvents();
            fileStoragePort.upload(params.fileContent().getInputStream(), file.getId());
            try {
                file = File.reconstruct(
                        file.getId(),
                        1,
                        file.getUploaderId(),
                        file.getFileName().getValue(),
                        file.getSize().getValue(),
                        file.getTotalPages().getValue(),
                        file.getCreationTime().getValue()
                );
                domainEvents.forEach(
                        file::addDomainEvent
                );
                fileWriter.save(file);
            } catch (Exception e) {
                fileWriter.deleteById(file.getId());
                throw e;
            }
            return file.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
