package studydocs.exception;


import lombok.Getter;

import java.util.UUID;


@Getter
public class DocumentNotFoundException extends RuntimeException {
    private final int errorCode = 404;
    public DocumentNotFoundException(UUID id) {
        super("Document not found: " + id);
    }
}