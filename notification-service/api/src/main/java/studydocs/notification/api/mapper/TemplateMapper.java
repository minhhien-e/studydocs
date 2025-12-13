package studydocs.notification.api.mapper;

import studydocs.notification.api.dto.request.template.*;
import studydocs.notification.application.dto.command.template.*;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.dto.query.template.GetTemplateByChannelQuery;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;

public final class TemplateMapper {
    /// Create
    public static AddTemplateCommand toCommand(AddTemplateRequest request) {
        return AddTemplateCommand.builder()
                .name(request.name())
                .channel(request.channel())
                .subjectTemplate(request.subjectTemplate())
                .bodyTemplate(request.bodyTemplate())
                .description(request.description())
                .build();
    }

    /// Read
    public static GetAllTemplateQuery toQuery(GetAllTemplateRequest request) {
        return GetAllTemplateQuery.builder().build();
    }

    public static GetTemplateByChannelQuery toQuery(GetTemplateByChannelRequest request) {
        return GetTemplateByChannelQuery.builder()
                .channel(request.channel())
                .build();
    }

    public static SearchTemplateByNameQuery toQuery(SearchTemplateByNameRequest request) {
        return SearchTemplateByNameQuery.builder()
                .name(request.templateName())
                .build();
    }

    /// Update
    public static EditTemplateBodyCommand toCommand(EditTemplateBodyRequest request) {
        return EditTemplateBodyCommand.builder()
                .templateId(request.getTemplateId())
                .newBody(request.getNewBody())
                .build();
    }

    public static EditTemplateSubjectCommand toCommand(EditTemplateSubjectRequest request) {
        return EditTemplateSubjectCommand.builder()
                .templateId(request.getTemplateId())
                .newSubject(request.getNewSubject())
                .build();
    }

    public static EditTemplateDescriptionCommand toCommand(EditTemplateDescriptionRequest request) {
        return EditTemplateDescriptionCommand.builder()
                .templateId(request.getTemplateId())
                .newDescription(request.getNewDescription())
                .build();
    }

    public static RenameTemplateCommand toCommand(RenameTemplateRequest request) {
        return RenameTemplateCommand.builder()
                .templateId(request.getTemplateId())
                .newName(request.getNewName())
                .build();
    }
    /// Delete
}
