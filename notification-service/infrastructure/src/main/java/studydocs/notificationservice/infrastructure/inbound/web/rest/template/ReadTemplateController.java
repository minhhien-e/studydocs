package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByChannelInput;
import studydocs.notificationservice.application.dto.input.template.read.SearchTemplateByNameInput;
import studydocs.notificationservice.application.usecase.template.read.GetAllTemplateUseCase;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByChannelUseCase;
import studydocs.notificationservice.application.usecase.template.read.SearchTemplateByNameUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.TemplateResponse;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
public class ReadTemplateController {
    private final GetTemplateByChannelUseCase getTemplateByChannelUseCase;
    private final GetAllTemplateUseCase getAllTemplateUseCase;
    private final SearchTemplateByNameUseCase searchTemplateByNameUseCase;

    @GetMapping("/channel/{channelName}")
    public ResponseEntity<?> getByChannel(@PathVariable("channelName") String channelName) {
        var inputModel = new GetTemplateByChannelInput(channelName);
        var result = getTemplateByChannelUseCase.execute(inputModel);
        var responses = result.stream().map(TemplateResponse::toResponse);
        return ResponseEntity.ok(ApiResponse.success(responses, "Lấy danh sách mẫu thông báo thành công"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var inputModel = new GetAllTemplateInput();
        var result = getAllTemplateUseCase.execute(inputModel);
        var responses = result.stream().map(TemplateResponse::toResponse);
        return ResponseEntity.ok(ApiResponse.success(responses, "Lấy danh sách mẫu thông báo thành công"));
    }

    public ResponseEntity<?> searchByName(@RequestParam("name") String name) {
        var inputModel = new SearchTemplateByNameInput(name);
        var result = searchTemplateByNameUseCase.execute(inputModel);
        var responses = result.stream().map(TemplateResponse::toResponse);
        return ResponseEntity.ok(ApiResponse.success(responses, "Tìm kiếm mẫu thông báo thành công"));
    }

}
