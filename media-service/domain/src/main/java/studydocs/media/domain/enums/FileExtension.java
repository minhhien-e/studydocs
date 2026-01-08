package studydocs.media.domain.enums;

import studydocs.media.domain.exception.file.FileNotSupportedException;
import studydocs.media.domain.exception.file.InvalidFileFormatException;

import java.util.Locale;

public enum FileExtension {
    // Text & documents
    TXT,
    PDF,
    DOCX,
    PPT,
    PPTX,
    XLS,
    XLSX,

    // Images
    PNG,
    JPG,
    JPEG,
    GIF,
    WEBP,

    // Other safe formats
    CSV,
    JSON;

    public static FileExtension fromFileName(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            throw new InvalidFileFormatException();
        }

        String ext = fileName.substring(fileName.lastIndexOf('.') + 1)
                .toUpperCase(Locale.ROOT);
        try {
            return FileExtension.valueOf(ext);
        } catch (IllegalArgumentException e) {
            throw new FileNotSupportedException(ext);
        }
    }
}
