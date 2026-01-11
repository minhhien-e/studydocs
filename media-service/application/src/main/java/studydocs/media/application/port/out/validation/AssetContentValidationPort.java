package studydocs.media.application.port.out.validation;

import java.io.InputStream;

public interface AssetContentValidationPort {
    InputStream validate(InputStream content, String fileName);
}
