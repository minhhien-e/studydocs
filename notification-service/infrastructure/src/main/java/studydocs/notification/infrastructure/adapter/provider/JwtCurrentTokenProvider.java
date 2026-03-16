package studydocs.notification.infrastructure.adapter.provider;

import org.springframework.stereotype.Component;
import studydocs.notification.application.port.in.provider.CurrentTokenProvider;
@Component
public class JwtCurrentTokenProvider implements CurrentTokenProvider {
    @Override
    public String getCurrentToken() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getCredentials() == null) {
//            return null;
//        }
//        return authentication.getCredentials().toString();
        return "";
    }
}
