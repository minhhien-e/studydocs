package studydocs.media.infrastructure.adapter.counter;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.processing.PageCounterPort;
import studydocs.media.domain.enums.FileExtension;

import java.io.InputStream;
@Component
public class PptxPageCounterAdapter implements PageCounterPort {
    @Override
    public int countPages(InputStream inputStream) {
        try (XMLSlideShow ppt = new XMLSlideShow(inputStream)) {
            return ppt.getSlides().size();
        } catch (Exception e) {
            throw new RuntimeException("Failed to count PPTX slides", e);
        }
    }
    @Override
    public boolean supports(FileExtension ext) {
        return FileExtension.PPTX.equals(ext);
    }
}
