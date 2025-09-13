package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.template.read.GetAllTemplateUseCase;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByChannelUseCase;
import studydocs.notificationservice.application.usecase.template.read.SearchTemplateByNameUseCase;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.ErrorResponse;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.StandardApiResponses;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.SuccessfulResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetAllNotificationTemplateRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetNotificationTemplateByChannelRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.SearchNotificationTemplateByNameRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import static studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.TemplateRequestMapper.toInput;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
@Tag(name = "Mẫu thông báo", description = "API quản lý mẫu thông báo - tạo, xem, cập nhật và tìm kiếm mẫu thông báo")
public class ReadTemplateController {
    private final GetTemplateByChannelUseCase getTemplateByChannelUseCase;
    private final GetAllTemplateUseCase getAllTemplateUseCase;
    private final SearchTemplateByNameUseCase searchTemplateByNameUseCase;

    @Operation(summary = "Lấy mẫu thông báo theo kênh", description = "Lấy danh sách tất cả mẫu thông báo của một kênh cụ thể")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Lấy danh sách mẫu thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "400", code = "REQUIRED_FIELD_MISSING", message = "Tên kênh không hợp lệ"),
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy kênh với tên đã cho")
            }
    )
    @GetMapping("/channel/{channelName}")
    public ResponseEntity<?> getByChannel(@PathVariable("channelName") String channelName) {
        var request = new GetNotificationTemplateByChannelRequest(channelName);
        var inputModel = toInput(request);
        var result = getTemplateByChannelUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách mẫu thông báo thành công"));
    }

    @Operation(summary = "Lấy tất cả mẫu thông báo", description = "Lấy danh sách tất cả mẫu thông báo trong hệ thống")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Lấy danh sách mẫu thông báo thành công", data = Object.class)
    )
    @GetMapping
    public ResponseEntity<?> getAll() {
        var request = new GetAllNotificationTemplateRequest();
        var inputModel = toInput(request);
        var result = getAllTemplateUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách mẫu thông báo thành công"));
    }

    @Operation(summary = "Tìm kiếm mẫu thông báo theo tên", description = "Tìm kiếm mẫu thông báo dựa trên tên mẫu")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Tìm kiếm mẫu thông báo thành công", data = Object.class)
    )
    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam("name") String name) {
        var request = new SearchNotificationTemplateByNameRequest(name);
        var inputModel = toInput(request);
        var result = searchTemplateByNameUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(result, "Tìm kiếm mẫu thông báo thành công"));
    }

}
