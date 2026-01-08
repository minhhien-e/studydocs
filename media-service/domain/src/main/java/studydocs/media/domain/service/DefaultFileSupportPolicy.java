package studydocs.media.domain.service;

import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.policy.FileSupportPolicy;

public class DefaultFileSupportPolicy implements FileSupportPolicy {
    @Override
    public FileExtension supports(String fileName) {
        return FileExtension.fromFileName(fileName);
    }
}
