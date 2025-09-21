package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateBodyInput;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateDescriptionInput;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateNameInput;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateSubjectInput;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateBodyUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateDescriptionUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateNameUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateSubjectUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.UpdateTemplateBodyRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.UpdateTemplateDescriptionRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.UpdateTemplateNameRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.UpdateTemplateSubjectRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
public class UpdateTemplateController {
    private final UpdateTemplateDescriptionUseCase updateTemplateDescriptionUseCase;
    private final UpdateTemplateBodyUseCase updateTemplateBodyUseCase;
    private final UpdateTemplateNameUseCase updateTemplateNameUseCase;
    private final UpdateTemplateSubjectUseCase updateTemplateSubjectUseCase;

    @PatchMapping("/{name}/update-name")
    public ResponseEntity<?> updateName(@PathVariable String name, @RequestBody UpdateTemplateNameRequest request) {

        var inputModel = new UpdateTemplateNameInput(name, request.newName());
        updateTemplateNameUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật tên mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/update-body")
    public ResponseEntity<?> updateBody(@PathVariable String name, @RequestBody UpdateTemplateBodyRequest request) {
        var inputModel = new UpdateTemplateBodyInput(name, request.newBody());
        updateTemplateBodyUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật nội dung mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/update-subject")
    public ResponseEntity<?> updateSubject(@PathVariable String name, @RequestBody UpdateTemplateSubjectRequest request) {
        var inputModel = new UpdateTemplateSubjectInput(name, request.newSubject());
        updateTemplateSubjectUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật tiêu đề mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/update-description")
    public ResponseEntity<?> updateDescription(@PathVariable String name, @RequestBody UpdateTemplateDescriptionRequest request) {
        var inputModel = new UpdateTemplateDescriptionInput(name, request.newDescription());
        updateTemplateDescriptionUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật mô tả mẫu thông báo thành công"));
    }
}
