package studydocs.media.domain.enums;

import studydocs.media.domain.exception.file.AssetNotSupportedException;
import studydocs.media.domain.exception.file.InvalidAssetFormatException;

import java.util.Locale;

public enum FileExtension {
    // Text & documents
    TXT(null),
    PDF(new byte[]{(byte) 0x25, (byte) 0x50, (byte) 0x44, (byte) 0x46}), // %PDF
    DOCX(new byte[]{(byte) 0x50, (byte) 0x4B, (byte) 0x03, (byte) 0x04}), // PK..
    PPT(new byte[]{(byte) 0xD0, (byte) 0xCF, (byte) 0x11, (byte) 0xE0}),
    PPTX(new byte[]{(byte) 0x50, (byte) 0x4B, (byte) 0x03, (byte) 0x04}),
    XLS(new byte[]{(byte) 0xD0, (byte) 0xCF, (byte) 0x11, (byte) 0xE0}),
    XLSX(new byte[]{(byte) 0x50, (byte) 0x4B, (byte) 0x03, (byte) 0x04}),

    // Images
    PNG(new byte[]{(byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47}),
    JPG(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF}),
    JPEG(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF}),
    GIF(new byte[]{(byte) 0x47, (byte) 0x49, (byte) 0x46, (byte) 0x38}), // GIF8
    WEBP(new byte[]{(byte) 0x52, (byte) 0x49, (byte) 0x46, (byte) 0x46}), // RIFF

    // Other safe formats
    CSV(null),
    JSON(null);

    private final byte[] magicBytes;

    FileExtension(byte[] magicBytes) {
        this.magicBytes = magicBytes;
    }

    public boolean validateSignature(byte[] fileHeader) {
        if (this.magicBytes == null) {
            return true; // No magic bytes defined, skip check (e.g. text files)
        }
        if (fileHeader == null || fileHeader.length < this.magicBytes.length) {
            return false;
        }
        for (int i = 0; i < this.magicBytes.length; i++) {
            if (fileHeader[i] != this.magicBytes[i]) {
                return false;
            }
        }
        return true;
    }

    public static FileExtension fromFileName(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            throw new InvalidAssetFormatException();
        }

        String ext = fileName.substring(fileName.lastIndexOf('.') + 1)
                .toUpperCase(Locale.ROOT);
        try {
            return FileExtension.valueOf(ext);
        } catch (IllegalArgumentException e) {
            throw new AssetNotSupportedException(ext);
        }
    }
}
