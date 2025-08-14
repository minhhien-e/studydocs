package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.template;

import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByChannelInput;
import studydocs.notificationservice.application.dto.input.template.read.SearchTemplateByNameInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetAllNotificationTemplateRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetNotificationTemplateByChannelRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.SearchNotificationTemplateByNameRequest;

public class ReadTemplateRequestMapper {
    //region Get All
    public static GetAllTemplateInput toInput(GetAllNotificationTemplateRequest request) {
        return new GetAllTemplateInput();
    }

    //endregion
    //region Get By Channel
    public static GetTemplateByChannelInput toInput(GetNotificationTemplateByChannelRequest request) {
        return new GetTemplateByChannelInput(request.channel());
    }

    //endregion
    //region Search By Name
    public static SearchTemplateByNameInput toInput(SearchNotificationTemplateByNameRequest request) {
        return new SearchTemplateByNameInput(request.name());
    }
    //endregion
}
