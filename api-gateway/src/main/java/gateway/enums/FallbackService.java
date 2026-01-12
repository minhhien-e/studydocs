package gateway.enums;

public enum FallbackService {
    USER("User"),
    AUTHENTICATION("Authentication"),
    NOTIFICATION("Notification"),
    REVIEW("Review"),
    FOLLOW("Follow"),
    ACADEMIC("Academic"),
    DOCUMENT("Document"),
    MEDIA("Media");

    private final String displayName;

    FallbackService(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
