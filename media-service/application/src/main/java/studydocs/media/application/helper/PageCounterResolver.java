package studydocs.media.application.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.processing.PageCounterPort;
import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.exception.asset.AssetNotSupportedException;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PageCounterResolver {
    private final List<PageCounterPort> pageCounters;

    public int countPages(FileExtension fileExtension, InputStream fileContent) {
        return pageCounters.stream()
                .filter(counter -> counter.supports(fileExtension))
                .findFirst()
                .map(counter -> counter.countPages(fileContent))
                .orElseThrow(() -> new AssetNotSupportedException(fileExtension.name()));
    }
}
