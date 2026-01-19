package studydocs.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {
    @Id
    private UUID id;

    private String fullName;
    private String username;
    private String email;
    private String phoneNumber;
    private String avatarID;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
    private String school;
    private boolean isPrivate;
    private List<UUID> savedDocumentIds = new ArrayList<>();

    public UserEntity(UUID id, String fullName, String username, String email,
                      String phoneNumber, String avatarUrl, String gender,
                      LocalDate dateOfBirth, String address) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatarID = avatarUrl;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.isPrivate = false;
        this.savedDocumentIds = new ArrayList<>();
    }
}

