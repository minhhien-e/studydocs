package studydocs.notification.api.mapper;

import studydocs.notification.api.dto.request.template.*;
import studydocs.notification.application.dto.command.template.*;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.dto.query.template.GetTemplateByChannelQuery;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;
import studydocs.notification.application.dto.view.TemplateView;

public final class TemplateMapper {
    /// Command
    public static AddTemplateCommand toCommand(AddTemplateRequest request) {
        return AddTemplateCommand.builder()
                .name(request.name())
                .channel(request.channel())
                .subjectTemplate(request.subjectTemplate())
                .bodyTemplate(request.bodyTemplate())
                .description(request.description())
                .build();
    }

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

    /// Query
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

    /// View
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
