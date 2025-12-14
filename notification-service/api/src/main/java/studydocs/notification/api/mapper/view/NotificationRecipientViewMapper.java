package studydocs.notification.api.mapper.view;

import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.application.dto.view.NotificationRecipientView;

public final class NotificationRecipientViewMapper {
    
    private NotificationRecipientViewMapper() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static NotificationRecipientView toView(NotificationRecipientProjection projection) {
        return new NotificationRecipientView(
                projection.id(),
                extractSenderName(projection),
                projection.renderedSubject(),
                projection.renderedBody(),
                extractCategory(projection),
                projection.isRead(),
                projection.receivedAt(),
                projection.deletedAt()
        );
    }
    
    private static String extractSenderName(NotificationRecipientProjection projection) {
        if (projection.notification() != null) {
            return "User " + projection.notification().senderId();
        }
        return "Unknown Sender";
    }
    
    private static String extractCategory(NotificationRecipientProjection projection) {
        if (projection.notification() != null) {
            return projection.notification().type();
        }
        return "GENERAL";
    }
}
