package studydocs.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class SecurityUtils {

    public static UUID getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return UUID.fromString(jwt.getSubject());
        }

        // Fallback: Try to parse from Authorization header (Bypass Mode)
        try {
            var requestAttributes = (org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder
                    .getRequestAttributes();
            if (requestAttributes != null) {
                String authHeader = requestAttributes.getRequest().getHeader("Authorization");
                System.out.println("DEBUG SecurityUtils: Auth Header: " + authHeader); // DEBUG LOG

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    String[] chunks = token.split("\\.");
                    if (chunks.length > 1) {
                        java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
                        String payload = new String(decoder.decode(chunks[1]));
                        System.out.println("DEBUG SecurityUtils: Payload: " + payload); // DEBUG LOG

                        // Simple regex to find "sub":"..."
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\"sub\":\"([^\"]+)\"");
                        java.util.regex.Matcher matcher = pattern.matcher(payload);
                        if (matcher.find()) {
                            System.out.println("DEBUG SecurityUtils: Found UserID: " + matcher.group(1)); // DEBUG LOG
                            return UUID.fromString(matcher.group(1));
                        } else {
                            System.out.println("DEBUG SecurityUtils: Regex did not match 'sub' in payload");
                        }
                    }
                }
            } else {
                System.out.println("DEBUG SecurityUtils: RequestAttributes is null");
            }
        } catch (Exception e) {
            System.out.println("DEBUG SecurityUtils: Exception parsing token: " + e.getMessage());
            e.printStackTrace();
        }

        throw new RuntimeException("User not authenticated");
    }
}
