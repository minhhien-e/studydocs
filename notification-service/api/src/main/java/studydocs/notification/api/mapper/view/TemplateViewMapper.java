package studydocs.notification.api.mapper.view;

import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.view.TemplateView;

/**
 * Maps Template Projection to View for API responses.
 */
public final class TemplateViewMapper {
    
    private TemplateViewMapper() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    /**
     * Converts TemplateProjection to TemplateView.
     * Excludes internal template content (subject/body templates).
     */
    public static TemplateView toView(TemplateProjection projection) {
        return new TemplateView(
                projection.id(),
                projection.name(),
                projection.channel(),
                projection.description(),
                projection.createdAt(),
                projection.updatedTime()
        );
    }
}
