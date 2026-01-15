package studydocs.media.infrastructure.adapter.counter;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.processing.PageCounterPort;
import studydocs.media.domain.enums.FileExtension;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class PdfPageCounterAdapter implements PageCounterPort {

    @Override
    public int countPages(InputStream inputStream) {
        try (PDDocument document = PDDocument.load(inputStream)) {
            return document.getNumberOfPages();
        } catch (IOException e) {
            throw new RuntimeException("Failed to process PDF file", e);
        }
    }

    @Override
    public int countPages(java.nio.file.Path path) {
        try (PDDocument document = PDDocument.load(path.toFile())) {
            return document.getNumberOfPages();
        } catch (IOException e) {
            throw new RuntimeException("Failed to process PDF file", e);
        }
    }
    @Override
    public boolean supports(FileExtension ext) {
        return FileExtension.PDF.equals(ext);
    }
}
