package studydocs.media.infrastructure.adapter.counter;

import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.processing.PageCounterPort;
import studydocs.media.domain.enums.FileExtension;

import java.io.InputStream;

@Component
public class ImagePageCounterAdapter implements PageCounterPort {
    @Override
    public int countPages(InputStream inputStream) {
        return 1;
    }
    @Override
    public boolean supports(FileExtension ext) {
        return FileExtension.PNG.equals(ext) ||
               FileExtension.JPG.equals(ext) ||
               FileExtension.JPEG.equals(ext) ||
               FileExtension.GIF.equals(ext) ||
               FileExtension.WEBP.equals(ext);
    }
}
