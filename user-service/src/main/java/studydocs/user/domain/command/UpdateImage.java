package studydocs.user.domain.command;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Value(staticConstructor = "commandOf")
public class UpdateImage implements UserCommand{
    UUID userId;
    MultipartFile image;
}