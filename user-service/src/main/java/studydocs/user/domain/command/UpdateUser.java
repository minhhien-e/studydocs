package studydocs.user.domain.command;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;



import java.util.UUID;

@Value(staticConstructor = "commandOf")
public class UpdateUser implements UserCommand {
    UUID userId;
    String fullName;
    String username;
    String email;
    String phoneNumber;
    String avatarUrl;
    String gender;
    LocalDate dateOfBirth;
    String address;
    String school;

    LocalDateTime timestamp = LocalDateTime.now();
}
