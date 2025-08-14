package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateBodyUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateDescriptionUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateNameUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateSubjectUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.template.UpdateTemplateRequestMapper;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateBodyRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateDescriptionRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateNameRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateSubjectRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

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
        var inputModel   = UpdateTemplateRequestMapper.toInput(name, request);
        updateTemplateNameUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi tên mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/update-body")
    public ResponseEntity<?> updateBody(@PathVariable String name, @RequestBody UpdateTemplateBodyRequest request) {
        var inputModel = UpdateTemplateRequestMapper.toInput(name, request);
        updateTemplateBodyUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi nội dung mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/update-subject")
    public ResponseEntity<?> updateSubject(@PathVariable String name, @RequestBody UpdateTemplateSubjectRequest request) {
        var inputModel = UpdateTemplateRequestMapper.toInput(name, request);
        updateTemplateSubjectUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi tiêu đề mẫu thông báo thành công"));
    }

    @PatchMapping("/{name}/update-description")
    public ResponseEntity<?> updateDescription(@PathVariable String name, @RequestBody UpdateTemplateDescriptionRequest request) {
        var inputModel = UpdateTemplateRequestMapper.toInput(name, request);
        updateTemplateDescriptionUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi miêu tả thông báo thành công"));
    }
}
