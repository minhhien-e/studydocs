package studydocs.media.infrastructure.adapter.counter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.processing.PageCounterPort;
import studydocs.media.domain.enums.FileExtension;

import java.io.InputStream;

@Component
public class XlsxPageCounterAdapter implements PageCounterPort {

    @Override
    public int countPages(InputStream inputStream) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            return workbook.getNumberOfSheets();
        } catch (Exception e) {
            throw new RuntimeException("Failed to count XLSX pages", e);
        }
    }
    @Override
    public boolean supports(FileExtension ext) {
        return FileExtension.XLSX.equals(ext);
    }
}
