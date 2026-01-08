package studydocs.media.domain.policy;

import studydocs.media.domain.enums.FileExtension;

public interface FileSupportPolicy {
    FileExtension supports(String extension);
}
