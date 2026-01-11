package studydocs.media.infrastructure.adapter.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.validation.AssetContentValidationPort;
import studydocs.media.domain.policy.AssetSupportPolicy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

@Component
@RequiredArgsConstructor
public class AssetContentValidationAdapter implements AssetContentValidationPort {

    private final AssetSupportPolicy assetSupportPolicy;

    @Override
    public InputStream validate(InputStream content, String fileName) {
        try {
            PushbackInputStream pushbackInputStream = new PushbackInputStream(content, 8);
            byte[] header = new byte[8];
            int bytesRead = pushbackInputStream.read(header);

            if (bytesRead > 0) {
                byte[] bytesToValidate = new byte[bytesRead];
                System.arraycopy(header, 0, bytesToValidate, 0, bytesRead);

                assetSupportPolicy.supports(fileName, () -> new ByteArrayInputStream(bytesToValidate));

                pushbackInputStream.unread(bytesToValidate);
            } else {
                assetSupportPolicy.supports(fileName, () -> new ByteArrayInputStream(new byte[0]));
            }

            return pushbackInputStream;

        } catch (IOException e) {
            throw new RuntimeException("Error performing content validation for file: " + fileName, e);
        }
    }
}
