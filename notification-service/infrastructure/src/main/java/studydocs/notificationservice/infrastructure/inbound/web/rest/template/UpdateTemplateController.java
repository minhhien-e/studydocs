package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateBodyUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateDescriptionUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateNameUseCase;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateSubjectUseCase;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.ErrorResponse;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.StandardApiResponses;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.SuccessfulResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateBodyRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateDescriptionRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateNameRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete.UpdateTemplateSubjectRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import static studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.TemplateRequestMapper.toInput;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
@Tag(name = "Mẫu thông báo", description = "API quản lý mẫu thông báo - tạo, xem, cập nhật và tìm kiếm mẫu thông báo")
public class UpdateTemplateController {
    private final UpdateTemplateDescriptionUseCase updateTemplateDescriptionUseCase;
    private final UpdateTemplateBodyUseCase updateTemplateBodyUseCase;
    private final UpdateTemplateNameUseCase updateTemplateNameUseCase;
    private final UpdateTemplateSubjectUseCase updateTemplateSubjectUseCase;

    @Operation(summary = "Cập nhật tên mẫu thông báo", description = "Cập nhật tên của một mẫu thông báo hiện có")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Cập nhật tên mẫu thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "400", code = "REQUIRED_FIELD_MISSING", message = "Tên mẫu thông báo không hợp lệ"),
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy mẫu thông báo với tên đã cho"),
                    @ErrorResponse(statusCode = "409", code = "RESOURCE_ALREADY_EXISTS", message = "Tên mẫu thông báo mới đã tồn tại")
            }
    )
    @PatchMapping("/{name}/update-name")
    public ResponseEntity<?> updateName(@PathVariable String name, @RequestBody UpdateTemplateNameRequest request) {
        var inputModel = toInput(name, request);
        updateTemplateNameUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật tên mẫu thông báo thành công"));
    }

    @Operation(summary = "Cập nhật nội dung mẫu thông báo", description = "Cập nhật nội dung chính của một mẫu thông báo hiện có")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Cập nhật nội dung mẫu thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "400", code = "REQUIRED_FIELD_MISSING", message = "Tên mẫu thông báo không hợp lệ"),
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy mẫu thông báo với tên đã cho")
            }
    )
    @PatchMapping("/{name}/update-body")
    public ResponseEntity<?> updateBody(@PathVariable String name, @RequestBody UpdateTemplateBodyRequest request) {
        var inputModel = toInput(name, request);
        updateTemplateBodyUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật nội dung mẫu thông báo thành công"));
    }

    @Operation(summary = "Cập nhật tiêu đề mẫu thông báo", description = "Cập nhật tiêu đề của một mẫu thông báo hiện có")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Cập nhật tiêu đề mẫu thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "400", code = "REQUIRED_FIELD_MISSING", message = "Tên mẫu thông báo không hợp lệ"),
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy mẫu thông báo với tên đã cho"),
            }
    )
    @PatchMapping("/{name}/update-subject")
    public ResponseEntity<?> updateSubject(@PathVariable String name, @RequestBody UpdateTemplateSubjectRequest request) {
        var inputModel = toInput(name, request);
        updateTemplateSubjectUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật tiêu đề mẫu thông báo thành công"));
    }

    @Operation(summary = "Cập nhật mô tả mẫu thông báo", description = "Cập nhật mô tả của một mẫu thông báo hiện có")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Cập nhật mô tả mẫu thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "400", code = "REQUIRED_FIELD_MISSING", message = "Tên mẫu thông báo không hợp lệ"),
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy mẫu thông báo với tên đã cho"),
            }
    )
    @PatchMapping("/{name}/update-description")
    public ResponseEntity<?> updateDescription(@PathVariable String name, @RequestBody UpdateTemplateDescriptionRequest request) {
        var inputModel = toInput(name, request);
        updateTemplateDescriptionUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Cập nhật mô tả mẫu thông báo thành công"));
    }
}
