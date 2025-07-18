package studydocs.notificationservice.adapter.input.rest.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.adapter.input.rest.request.AddTemplateRequest;
import studydocs.notificationservice.application.port.input.usecase.template.add.AddTemplateUseCase;
import studydocs.notificationservice.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
public class TemplateController {
    private final AddTemplateUseCase addTemplateUseCase;

    public ResponseEntity<?> addTemplate(@Valid @RequestBody AddTemplateRequest request) {
        var inputModel = request.toInputModel();
        addTemplateUseCase.execute(inputModel);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED)
                .body(ApiResponse.success(null, "Thêm template thành công"));
    }
}
