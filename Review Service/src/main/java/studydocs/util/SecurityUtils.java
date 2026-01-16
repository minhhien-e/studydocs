package studydocs.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class SecurityUtils {

    public static UUID getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getName())) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "User is not authenticated (Anonymous)");
        }

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String subject = jwt.getSubject();
            try {
                return UUID.fromString(subject);
            } catch (IllegalArgumentException e) {
                // Try fallback if subject is not UUID (rare)
                System.out.println("WARN: JWT Subject is not UUID: " + subject);
            }
        }

        // Fallback or Try parsing Name if explicit UUID
        try {
            return UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException e) {
            // Check fallback header logic
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
                        // System.out.println("DEBUG SecurityUtils: Payload: " + payload);

                        // Simple regex to find "sub":"..."
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\"sub\":\"([^\"]+)\"");
                        java.util.regex.Matcher matcher = pattern.matcher(payload);
                        if (matcher.find()) {
                            return UUID.fromString(matcher.group(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("DEBUG SecurityUtils: Exception parsing token: " + e.getMessage());
        }

        throw new org.springframework.security.access.AccessDeniedException("User not authenticated or Invalid Token");
    }
}
