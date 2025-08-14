package studydocs.notificationservice.infrastructure.inbound.web.rest.template;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.template.read.GetAllTemplateUseCase;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByChannelUseCase;
import studydocs.notificationservice.application.usecase.template.read.SearchTemplateByNameUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.template.ReadTemplateRequestMapper;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetAllNotificationTemplateRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.GetNotificationTemplateByChannelRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.read.SearchNotificationTemplateByNameRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
public class ReadTemplateController {
    private final GetTemplateByChannelUseCase getTemplateByChannelUseCase;
    private final GetAllTemplateUseCase getAllTemplateUseCase;
    private final SearchTemplateByNameUseCase searchTemplateByNameUseCase;

    @GetMapping("/channel/{channelName}")
    public ResponseEntity<?> getByChannel(@PathVariable("channelName") String channelName) {
        var request = new GetNotificationTemplateByChannelRequest(channelName);
        var inputModel = ReadTemplateRequestMapper.toInput(request);
        var result = getTemplateByChannelUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách mẫu thông báo thành công"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var request = new GetAllNotificationTemplateRequest();
        var inputModel = ReadTemplateRequestMapper.toInput(request);
        var result = getAllTemplateUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách mẫu thông báo thành công"));
    }
    @GetMapping( "/search")
    public ResponseEntity<?> searchByName(@RequestParam("name") String name) {
        var request = new SearchNotificationTemplateByNameRequest(name);
        var inputModel = ReadTemplateRequestMapper.toInput(request);
        var result = searchTemplateByNameUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(result, "Lấy danh sách mẫu thông báo thành công"));
    }

}
