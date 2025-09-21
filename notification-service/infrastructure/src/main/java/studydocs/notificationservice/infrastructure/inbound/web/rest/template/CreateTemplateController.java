package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.application.dto.input.template.create.AddTemplateInput;
import studydocs.notificationservice.application.usecase.template.create.AddTemplateUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.AddTemplateRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
public class CreateTemplateController {
    private final AddTemplateUseCase addTemplateUseCase;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddTemplateRequest request) {
        var inputModel = new AddTemplateInput(request.name(), request.channel(), request.subjectTemplate(), request.bodyTemplate(), request.description());
        addTemplateUseCase.execute(inputModel);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED)
                .body(ApiResponse.success(null, "Tạo mẫu thông báo thành công"));
    }
}
