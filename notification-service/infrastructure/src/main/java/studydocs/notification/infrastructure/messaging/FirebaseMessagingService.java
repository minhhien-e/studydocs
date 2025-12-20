package studydocs.notification.infrastructure.messaging;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.RemoveFcmTokenCommand;
import studydocs.notification.application.port.in.bus.MediatorBusPort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FirebaseMessagingService {
    private final MediatorBusPort mediatorBusPort;

    public void sendMultiNotification(String title, String body, List<String> tokens) {
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokens)
                .setNotification(
                        Notification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build()
                )
                .build();
        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendEachForMulticast(message);
            handleResponse(tokens, response);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private void handleResponse(
            List<String> tokens,
            BatchResponse response
    ) {
        List<SendResponse> responses = response.getResponses();

        for (int i = 0; i < responses.size(); i++) {
            SendResponse res = responses.get(i);
            String token = tokens.get(i);

            if (!res.isSuccessful()) {
                Exception ex = res.getException();
                if (ex instanceof FirebaseMessagingException fcmEx) {
                    if (MessagingErrorCode.UNREGISTERED.equals(fcmEx.getMessagingErrorCode())) {
                        mediatorBusPort.send(new RemoveFcmTokenCommand(token));
                    }
                }
            }
        }
    }
}
