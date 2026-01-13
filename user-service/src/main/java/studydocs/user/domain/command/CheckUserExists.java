package studydocs.user.domain.command;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "commandOf")
public class CheckUserExists implements UserCommand{
    UUID userId;
}
