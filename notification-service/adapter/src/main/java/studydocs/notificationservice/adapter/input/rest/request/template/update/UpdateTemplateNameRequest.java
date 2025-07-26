package studydocs.notificationservice.adapter.input.rest.request.template.update;

import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.UpdateTemplateNameInputModel;

public record UpdateTemplateNameRequest( String oldName, String newName) {
    public UpdateTemplateNameInputModel toInputModel() {
        return new UpdateTemplateNameInputModel( oldName, newName);
    }
}
