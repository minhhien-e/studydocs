package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.application.usecase.template.create.AddTemplateUseCase;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.ErrorResponse;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.StandardApiResponses;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.SuccessfulResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.create.AddTemplateRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import static studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.TemplateRequestMapper.toInput;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
@Tag(name = "Mẫu thông báo", description = "API quản lý mẫu thông báo - tạo, xem, cập nhật và tìm kiếm mẫu thông báo")
public class CreateTemplateController {
    private final AddTemplateUseCase addTemplateUseCase;

    @Operation(summary = "Tạo mẫu thông báo mới", description = "Tạo một mẫu thông báo mới với thông tin chi tiết")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Tạo mẫu thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "400", code = "REQUIRED_FIELD_MISSING", message = "Dữ liệu yêu cầu không hợp lệ"),
                    @ErrorResponse(statusCode = "409", code = "TEMPLATE_ALREADY_EXISTS", message = "Mẫu thông báo với tên này đã tồn tại")
            }
    )
    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddTemplateRequest request) {
        var inputModel = toInput(request);
        addTemplateUseCase.execute(inputModel);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED)
                .body(ApiResponse.success(null, "Tạo mẫu thông báo thành công"));
    }
}
