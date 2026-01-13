package studydocs.user.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyRegisterSuccessRequest {
    private String email;
    private String phoneNumber;
    private String newEmail;
    private String newPhoneNumber;
}
