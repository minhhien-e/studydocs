package studydocs.user.domain.command;

import lombok.Value;

@Value(staticConstructor = "commandOf")
public class GetUserCount implements UserCommand {

}