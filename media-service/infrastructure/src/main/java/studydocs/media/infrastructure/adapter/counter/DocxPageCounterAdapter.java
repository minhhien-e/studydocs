package studydocs.media.infrastructure.adapter.counter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.processing.PageCounterPort;
import studydocs.media.domain.enums.FileExtension;

import java.io.InputStream;
@Component
public class DocxPageCounterAdapter implements PageCounterPort {
    @Override
    public int countPages(InputStream inputStream) {
        try (XWPFDocument doc = new XWPFDocument(inputStream)) {
            int pages = doc.getProperties()
                    .getExtendedProperties()
                    .getUnderlyingProperties()
                    .getPages();
            if (pages <= 0) {
                throw new IllegalStateException("DOCX page count not available");
            }
            return pages;
        } catch (Exception e) {
            throw new RuntimeException("Failed to count DOCX pages", e);
        }    }

    @Override
    public boolean supports(FileExtension ext) {
        return FileExtension.DOCX.equals(ext);
    }
}
