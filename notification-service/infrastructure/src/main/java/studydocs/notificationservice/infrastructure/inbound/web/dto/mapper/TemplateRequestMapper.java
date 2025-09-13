package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper;

import studydocs.notificationservice.application.dto.input.template.create.AddTemplateInput;
import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByChannelInput;
import studydocs.notificationservice.application.dto.input.template.read.SearchTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateBodyInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateDescriptionInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateSubjectInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.create.AddTemplateRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetAllNotificationTemplateRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetNotificationTemplateByChannelRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.SearchNotificationTemplateByNameRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateBodyRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateDescriptionRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateNameRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateSubjectRequest;

public class TemplateRequestMapper {
    public static AddTemplateInput toInput(AddTemplateRequest request) {
        return new AddTemplateInput(
                request.name(),
                request.channel(),
                request.subjectTemplate(),
                request.bodyTemplate(),
                request.description()
        );
    }

    public static GetAllTemplateInput toInput(GetAllNotificationTemplateRequest request) {
        return new GetAllTemplateInput();
    }

    public static GetTemplateByChannelInput toInput(GetNotificationTemplateByChannelRequest request) {
        return new GetTemplateByChannelInput(request.channel());
    }

    public static SearchTemplateByNameInput toInput(SearchNotificationTemplateByNameRequest request) {
        return new SearchTemplateByNameInput(request.name());
    }

    public static void setFindAttribute(String name, UpdateTemplateByNameInput input) {
        input.setName(name);
    }

    public static UpdateTemplateBodyInput toInput(String name, UpdateTemplateBodyRequest request) {
        var input = new UpdateTemplateBodyInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }

    public static UpdateTemplateNameInput toInput(String name, UpdateTemplateNameRequest request) {
        var input = new UpdateTemplateNameInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }

    public static UpdateTemplateDescriptionInput toInput(String name, UpdateTemplateDescriptionRequest request) {
        var input = new UpdateTemplateDescriptionInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }

    public static UpdateTemplateSubjectInput toInput(String name, UpdateTemplateSubjectRequest request) {
        var input = new UpdateTemplateSubjectInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }
}
