package studydocs.notification.api.mapper;

import studydocs.notification.api.dto.request.template.*;
import studydocs.notification.application.dto.command.template.*;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.dto.query.template.GetTemplateByChannelQuery;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;

public final class TemplateMapper {
    /// Create
    public static AddTemplateCommand toCommand(AddTemplateRequest request) {
        return new AddTemplateCommand(request.name(), request.channel(), request.subjectTemplate(), request.bodyTemplate(), request.description());
    }

    /// Read
    public static GetAllTemplateQuery toQuery(GetAllTemplateRequest request) {
        return new GetAllTemplateQuery();
    }

    public static GetTemplateByChannelQuery toQuery(GetTemplateByChannelRequest request) {
        return new GetTemplateByChannelQuery(request.channel());
    }

    public static SearchTemplateByNameQuery toQuery(SearchTemplateByNameRequest request) {
        return new SearchTemplateByNameQuery(request.templateName());
    }

    /// Update
    public static EditTemplateBodyCommand toCommand(EditTemplateBodyRequest request) {
        return new EditTemplateBodyCommand(request.getTemplateId(), request.getNewBody());
    }

    public static EditTemplateSubjectCommand toCommand(EditTemplateSubjectRequest request) {
        return new EditTemplateSubjectCommand(request.getTemplateId(), request.getNewSubject());
    }

    public static EditTemplateDescriptionCommand toCommand(EditTemplateDescriptionRequest request) {
        return new EditTemplateDescriptionCommand(request.getTemplateId(), request.getNewDescription());
    }

    public static RenameTemplateCommand toCommand(RenameTemplateRequest request) {
        return new RenameTemplateCommand(request.getTemplateId(), request.getNewName());
    }
    /// Delete
}
