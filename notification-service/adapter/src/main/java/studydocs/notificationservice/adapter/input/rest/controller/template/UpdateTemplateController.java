package studydocs.notificationservice.adapter.input.rest.controller.template;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.adapter.input.rest.request.template.update.UpdateTemplateBodyRequest;
import studydocs.notificationservice.adapter.input.rest.request.template.update.UpdateTemplateDescriptionRequest;
import studydocs.notificationservice.adapter.input.rest.request.template.update.UpdateTemplateNameRequest;
import studydocs.notificationservice.adapter.input.rest.request.template.update.UpdateTemplateSubjectRequest;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateBodyUseCase;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateDescriptionUseCase;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateNameUseCase;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateSubjectUseCase;
import studydocs.notificationservice.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
public class UpdateTemplateController {
    private final UpdateTemplateDescriptionUseCase updateTemplateDescriptionUseCase;
    private final UpdateTemplateBodyUseCase updateTemplateBodyUseCase;
    private final UpdateTemplateNameUseCase updateTemplateNameUseCase;
    private final UpdateTemplateSubjectUseCase updateTemplateSubjectUseCase;

    @PutMapping("/name")
    public ResponseEntity<?> updateName(@RequestBody UpdateTemplateNameRequest request) {
        var inputModel = request.toInputModel();
        updateTemplateNameUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi tên mẫu thông báo thành công"));
    }

    @PutMapping("/body")
    public ResponseEntity<?> updateBody(@RequestBody UpdateTemplateBodyRequest request) {
        var inputModel = request.toInputModel();
        updateTemplateBodyUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi nội dung mẫu thông báo thành công"));
    }

    @PutMapping("/subject")
    public ResponseEntity<?> updateSubject(@RequestBody UpdateTemplateSubjectRequest request) {
        var inputModel = request.toInputModel();
        updateTemplateSubjectUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi tiêu đề mẫu thông báo thành công"));
    }

    @PutMapping("/description")
    public ResponseEntity<?> updateDescription(@RequestBody UpdateTemplateDescriptionRequest request) {
        var inputModel = request.toInputModel();
        updateTemplateDescriptionUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Thay đổi miêu tả thông báo thành công"));
    }
}
