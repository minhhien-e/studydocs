package studydocs.notification.infrastructure.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp firebaseApp() {
        try {
            InputStream serviceAccount =
                    FirebaseConfig.class
                            .getClassLoader()
                            .getResourceAsStream("firebase/firebase-adminsdk.json");

            if (serviceAccount == null) {
                throw new IllegalStateException("firebase-adminsdk.json not found in resources");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            List<FirebaseApp> apps = FirebaseApp.getApps();
            if (apps != null && !apps.isEmpty()) {
                return apps.get(0);
            }
            return FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }

}
