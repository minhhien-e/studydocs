package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.dto.input.template.update.EditTemplateBodyInput;
import studydocs.notificationservice.application.dto.input.template.update.EditTemplateDescriptionInput;
import studydocs.notificationservice.application.dto.input.template.update.EditTemplateSubjectInput;
import studydocs.notificationservice.application.dto.input.template.update.RenameTemplateInput;
import studydocs.notificationservice.application.usecase.template.update.EditTemplateBodyUseCase;
import studydocs.notificationservice.application.usecase.template.update.EditTemplateDescriptionUseCase;
import studydocs.notificationservice.application.usecase.template.update.EditTemplateSubjectUseCase;
import studydocs.notificationservice.application.usecase.template.update.RenameTemplateUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.EditTemplateBodyRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.EditTemplateDescriptionRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.EditTemplateSubjectRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.RenameTemplateRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('notification.template.edit')")
public class UpdateTemplateController {
    private final EditTemplateDescriptionUseCase editTemplateDescriptionUseCase;
    private final EditTemplateBodyUseCase editTemplateBodyUseCase;
    private final RenameTemplateUseCase renameTemplateUseCase;
    private final EditTemplateSubjectUseCase editTemplateSubjectUseCase;

    @PatchMapping("/{name}/name")
    public ResponseEntity<?> rename(@PathVariable String name, @RequestBody RenameTemplateRequest request) {

        var inputModel = new RenameTemplateInput(name, request.newName());
        renameTemplateUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật tên mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/body")
    public ResponseEntity<?> editBody(@PathVariable String name, @RequestBody EditTemplateBodyRequest request) {
        var inputModel = new EditTemplateBodyInput(name, request.newBody());
        editTemplateBodyUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật nội dung mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/subject")
    public ResponseEntity<?> editSubject(@PathVariable String name, @RequestBody EditTemplateSubjectRequest request) {
        var inputModel = new EditTemplateSubjectInput(name, request.newSubject());
        editTemplateSubjectUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật tiêu đề mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/description")
    public ResponseEntity<?> editDescription(@PathVariable String name, @RequestBody EditTemplateDescriptionRequest request) {
        var inputModel = new EditTemplateDescriptionInput(name, request.newDescription());
        editTemplateDescriptionUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật mô tả mẫu thông báo thành công"));
    }
}
