package studydocs.exception;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(Long id) {
        super("Không tìm thấy tài liệu có id: " + id);
    }
}