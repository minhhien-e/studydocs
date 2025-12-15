package studydocs.notification.api.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notification.api.dto.request.template.*;
import studydocs.notification.api.helper.RequestExecutor;
import studydocs.notification.api.mapper.TemplateMapper;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications/templates")
@RequiredArgsConstructor
public class TemplateController {
    private final RequestExecutor requestExecutor;

    /// Create
    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddTemplateRequest request) {
        return requestExecutor.execute(TemplateMapper::toCommand, request, HttpStatus.OK);
    }

    /// Read
    @GetMapping("/channel/{channelName}")
    public ResponseEntity<?> getByChannel(@PathVariable("channelName") String channelName) {
        var request = new GetTemplateByChannelRequest(channelName);
        return requestExecutor.executeAndMapView(
                TemplateMapper::toQuery,
                request,
                TemplateMapper::toView,
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(SearchTemplateByNameRequest request) {
        return requestExecutor.executeAndMapView(
                TemplateMapper::toQuery,
                request,
                TemplateMapper::toView,
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var request = new GetAllTemplateRequest();
        return requestExecutor.executeAndMapView(
                TemplateMapper::toQuery,
                request,
                TemplateMapper::toView,
                HttpStatus.OK
        );
    }

    /// Update
    @PatchMapping("/{id}/name")
    public ResponseEntity<?> rename(@PathVariable UUID id, @RequestBody RenameTemplateRequest request) {
        request.setTemplateId(id);
        return requestExecutor.execute(TemplateMapper::toCommand, request, HttpStatus.OK);
    }

    @PatchMapping("/{id}/body")
    public ResponseEntity<?> editBody(@PathVariable UUID id, @RequestBody EditTemplateBodyRequest request) {
        request.setTemplateId(id);
        return requestExecutor.execute(TemplateMapper::toCommand, request, HttpStatus.OK);
    }

    @PatchMapping("/{id}/subject")
    public ResponseEntity<?> editSubject(@PathVariable UUID id, @RequestBody EditTemplateSubjectRequest request) {
        request.setTemplateId(id);
        return requestExecutor.execute(TemplateMapper::toCommand, request, HttpStatus.OK);
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<?> editDescription(@PathVariable UUID id, @RequestBody EditTemplateDescriptionRequest request) {
        request.setTemplateId(id);
        return requestExecutor.execute(TemplateMapper::toCommand, request, HttpStatus.OK);
    }


}
