package studydocs.media.domain.aggregate;

import io.github.domain.aggregate.AggregateRoot;
import studydocs.media.domain.event.FileUploadedEvent;
import studydocs.media.domain.vo.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class File extends AggregateRoot {
    private UUID uploaderId;
    private FileName fileName;
    private FileSize size;
    private TotalPages totalPages;
    private FileCreationTime creationTime;

    /// Constructor
    private File(UUID id, long version) {
        super(id, version);
    }

    private File() {
        super();
        creationTime = FileCreationTime.now();
    }


    /// Factory method
    public static File create(
            UUID uploaderId,
            FileName fileName,
            FileSize size,
            TotalPages totalPages
    ) {
        File file = new File();
        file.uploaderId = uploaderId;
        file.fileName = fileName;
        file.size = size;
        file.totalPages = totalPages;
        file.addDomainEvent(new FileUploadedEvent(
                file.getId(),
                file.uploaderId
        ));
        return file;
    }

    public static File reconstruct(
            UUID id,
            long version,
            UUID uploaderId,
            String fileName,
            Long size,
            Integer totalPages,
            LocalDateTime createdAt
    ) {
        File file = new File(id, version);
        file.uploaderId = uploaderId;
        file.fileName = FileName.of(fileName);
        file.size = FileSize.of(size);
        file.totalPages = TotalPages.of(totalPages);
        file.creationTime = FileCreationTime.of(createdAt);
        return file;
    }
    /// Business Logic
    public void delete() {
    }
    /// Getters
    public UUID getUploaderId() {
        return uploaderId;
    }

    public FileName getFileName() {
        return fileName;
    }

    public FileSize getSize() {
        return size;
    }

    public TotalPages getTotalPages() {
        return totalPages;
    }

    public FileCreationTime getCreationTime() {
        return creationTime;
    }
}

