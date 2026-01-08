package studydocs.media.application.port.out.processing;

import studydocs.media.domain.enums.FileExtension;

import java.io.InputStream;

public interface PageCounterPort {
    int countPages(InputStream inputStream);

    boolean supports(FileExtension ext);
}
