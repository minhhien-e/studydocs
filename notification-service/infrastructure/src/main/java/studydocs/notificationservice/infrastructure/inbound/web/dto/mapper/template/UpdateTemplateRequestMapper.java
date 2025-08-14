package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.template;

import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateBodyInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateDescriptionInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateSubjectInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateBodyRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateDescriptionRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateNameRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateSubjectRequest;

public class UpdateTemplateRequestMapper {
    //region Update By Name
    public static void setFindAttribute(String name, UpdateTemplateByNameInput input) {
        input.setName(name);
    }

    //endregion
    //region  Update Body
    public static UpdateTemplateBodyInput toInput(String name, UpdateTemplateBodyRequest request) {
        var input = new UpdateTemplateBodyInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }

    //endregion
    //region Update Name
    public static UpdateTemplateNameInput toInput(String name, UpdateTemplateNameRequest request) {
        var input = new UpdateTemplateNameInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }

    //endregion
    //region Update Subject
    public static UpdateTemplateDescriptionInput toInput(String name, UpdateTemplateDescriptionRequest request) {
        var input = new UpdateTemplateDescriptionInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }

    //endregion
    public static UpdateTemplateSubjectInput toInput(String name, UpdateTemplateSubjectRequest request) {
        var input = new UpdateTemplateSubjectInput(request.getTemplateName());
        setFindAttribute(name, input);
        return input;
    }
}
